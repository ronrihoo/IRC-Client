package ircclient.irc.session.service;

public interface IrcService extends ConnectionService, MessageService {

    String getTimeStamp();

}
