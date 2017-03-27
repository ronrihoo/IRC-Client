package ircclient.irc.session.service;

public interface MessageService {

    String readLine();
    void sendLine(String line);

}
