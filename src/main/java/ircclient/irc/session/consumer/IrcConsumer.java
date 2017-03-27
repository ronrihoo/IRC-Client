package ircclient.irc.session.consumer;

import java.util.ArrayList;

public interface IrcConsumer extends FreeNodeSupport {

    void setNick(String nick);
    void setChannel(String channel);
    void setServer(String server);
    void setPort(int port);
    void connect(String server, int port);
    void join();
    void join(String channel);
    void login(String channel, String nick, String pass);
    void idle();
    void send(String line);     // not saved in post history
    String receive();
    String processLine(String line);
    void post(String line);     // is saved in post history
    void print(String line);
    void ping(String target);
    void pong();
    void leave();
    void disconnect();
    void updateUserList(ArrayList<String> userList);
    void setGuiStateToConnected();
    void setGuiStateToLoggedIn();
    void setGuiStateToNotLoggedIn();
    void setGuiStateToNotConnected();
    IrcConsumer getIrcClient();

}
