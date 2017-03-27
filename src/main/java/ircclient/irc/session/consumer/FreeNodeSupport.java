package ircclient.irc.session.consumer;

public interface FreeNodeSupport {

    void login(String nick, String pass, String realName, String login,
               int mode);

}
