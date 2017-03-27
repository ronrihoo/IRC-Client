package ircclient.irc.session.service;

public interface ConnectionService {

    boolean connect(String server, int port);
    void disconnect();

}
