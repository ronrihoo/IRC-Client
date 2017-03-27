package ircclient.irc.session.consumer;

import ircclient.irc.session.service.*;

import java.util.ArrayList;

public class IrcClient implements IrcConsumer {

    protected IrcService ircService = null;
    protected GuiService guiService = null;
    protected BotService bots = null;
    protected String nick = null;
    protected String channel = null;
    protected String server = null;
    protected int port;

    public IrcClient(IrcService ircService, GuiService guiService,
                     BotService bots) {
        this.ircService = ircService;
        this.guiService = guiService;
        this.bots = bots;
    }

    @Override
    public void setNick(String nick) {
        this.nick = nick;
    }

    @Override
    public void setChannel(String channel) {
        this.channel = channel;
    }

    @Override
    public void setServer(String server) {
        this.server = server;
    }

    @Override
    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public void connect(String server, int port) {
        ircService.connect(server, port);
    }

    @Override
    public void join() {

    }

    @Override
    public void join(String channel) {

    }

    @Override
    public void login(String channel, String nick, String pass) {

    }

    @Override
    public void login(String nick, String pass, String realName, String login,
                      int mode) {
        // FreeNode
    }

    @Override
    public void idle() {

    }

    @Override
    public void send(String line) {
        ircService.sendLine(line);
    }

    @Override
    public String receive() {
        return ircService.readLine();
    }

    @Override
    public String processLine(String line) {
        return null;
    }

    @Override
    public void post(String line) {
        send(line);
    }

    @Override
    public void print(String line) {
        guiService.printLine(line);
    }

    @Override
    public void ping(String target) {

    }

    @Override
    public void pong() {

    }

    @Override
    public void leave() {

    }

    @Override
    public void disconnect() {
        ircService.disconnect();
    }

    @Override
    public void updateUserList(ArrayList<String> userList) {
        guiService.updateUserList(userList);
    }

    @Override
    public void setGuiStateToConnected() {
        guiService.setDisconnectButton();
    }

    @Override
    public void setGuiStateToLoggedIn() {
        guiService.enablePostButton();
    }

    @Override
    public void setGuiStateToNotLoggedIn() {
        guiService.disablePostButton();
    }

    @Override
    public void setGuiStateToNotConnected() {
        guiService.setConnectButton();
        guiService.disablePostButton();
    }

    @Override
    public IrcConsumer getIrcClient() {
        return this;
    }


}
