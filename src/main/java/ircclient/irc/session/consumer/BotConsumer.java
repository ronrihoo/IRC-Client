package ircclient.irc.session.consumer;

public interface BotConsumer {

    boolean parseForCall(String str);
    String validateInput(String str);
    String makeApiCall(String str);
    String parseJsonContents(String str);
    String returnResult(String str);

}
