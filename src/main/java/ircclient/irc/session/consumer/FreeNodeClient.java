package ircclient.irc.session.consumer;

import ircclient.irc.session.service.BotService;
import ircclient.irc.session.service.GuiService;
import ircclient.irc.session.service.IrcService;
import ircclient.irc.session.service.freenode.FreeNode;
import ircclient.log.Report;

import java.io.IOException;
import java.util.ArrayList;

public class FreeNodeClient extends IrcClient {

    FreeNode freeNode = null;

    public FreeNodeClient(IrcService ircService, GuiService guiService,
                          BotService bots, FreeNode freeNode) {
        super(ircService, guiService, bots);
        this.freeNode = freeNode;
    }

    @Override
    public void setNick(String nick) {
        super.setNick(nick);
        freeNode.setNick(nick);
    }

    @Override
    public void setChannel(String channel) {
        super.setChannel(channel);
        freeNode.setChannel(channel);
    }

    @Override
    public void connect(String server, int port) {
        super.connect(server, port);
        login(freeNode.getNick(), "", "", "", 8);
    }

    @Override
    public void login(String nick, String pass, String realName, String login,
                      int mode) {
        String line = null;
        send(freeNode.login(nick, pass, realName, login, mode));
        while ((line = receive()) != null) {
            line = freeNode.listenForLoginConfirmation(line);
            if (line.equals(freeNode.LOGIN_CONFIRMATION_CODE)) {
                break;
            } else if (line.equals(freeNode.NICKNAME_ALREADY_IN_USE)) {
                setGuiStateToNotConnected();
                print(line);
                print(freeNode.NICKNAME_ALREADY_IN_USE_PROMPT);
                line = null;
                break;
            } else if (freeNode.isReadable()) {
                print(line);
            }
            keepUpWithConnectionStatus();
        }
        if (line != null) {
            while ((line = receive()) != null) {
                line = freeNode.reformatLine(line);
                if (line != null) {
                    if (freeNode.isWritable()) {
                        send(line);
                        Report.println(line);
                    } else if (freeNode.isLoggedIn()) {
                        break;
                    } else {
                        print(line);
                    }
                }
                keepUpWithConnectionStatus();
            }
            setGuiStateToConnected();
            join();
        }
    }

    @Override
    public void join() {
        // TODO: should be a while loop that tests for whether user has logged in
        if (channel != null) {
            freeNode.setChannel(channel);
        }
        send(freeNode.join());
        // TODO: leaving to idle() at this point is a bad idea
        // Should only do this once login has been verified.
        setGuiStateToLoggedIn();
        idle();
    }

    @Override
    public void idle() {
        String line = null;
        while ((line = receive()) != null) {
            line = processLine(line);
            if (line != null) {
                if (freeNode.isWritable()) {
                    send(line);
                    if (freeNode.isPrintable()) {
                        print(line);
                    }
                    Report.println(line);
                } else if (!freeNode.isConnected() &&
                        !freeNode.isLoggedIn()) {
                    setGuiStateToNotConnected();
                    break;
                } else {
                    print(line);
                    if (freeNode.isParsable()) {
                        try {
                            invokeBot(line);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            maintainUserListChanges();
            keepUpWithConnectionStatus();
        }
        setGuiStateToNotConnected();
    }

    @Override
    public String processLine(String line) {
        return freeNode.reformatLine(line);
    }

    @Override
    public void post(String line) {
        super.post(freeNode.postMessage(line));
        print(freeNode.readMessage(freeNode.getNick(), line));
        try {
            invokeBot(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void post(String line, boolean useBot) {
        super.post(freeNode.postMessage(line));
        print(freeNode.readMessage(freeNode.getNick(), line));
        if (useBot) {
            try {
                invokeBot(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void leave() {
        send(freeNode.part());
    }

    @Override
    public void disconnect() {
        send(freeNode.quit());
    }

    public void maintainUserListChanges() {
        if (freeNode.userListHasChanged()) {
            updateUserList(freeNode.getUsers());
            freeNode.setUserListSignal(false);
        }
    }

    public void keepUpWithConnectionStatus() {
        if (!freeNode.isConnected() && !freeNode.isLoggedIn()) {
            setGuiStateToNotConnected();
        }
        /**/
        else if (freeNode.isConnected() && freeNode.isLoggedIn()) {
            setGuiStateToConnected();
        }
        /**/
    }

    @Override
    public IrcConsumer getIrcClient() {
        return this;
    }

    @Override
    public void print(String line) {
        super.print(ircService.getTimeStamp() + line);
    }

    public String getTimeStamp() {
        return ircService.getTimeStamp();
    }

    public void printBotReport(String str) {
        new Thread(new Runnable(){
            public void run(){
                ArrayList<String> reports = bots.getReport(str);
                for (String report : reports) {
                    if (report != null) {
                        post(report, false);
                    }
                }
            }
        }).start();
    }

    public void invokeBot(String str) throws IOException {
        if (guiService.botIsEnabled()) {
            if (bots.containsSignal(str)) {
                printBotReport(str);
            }
        }
    }
}
