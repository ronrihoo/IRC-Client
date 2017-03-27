package ircclient.irc.session.service;

import java.util.ArrayList;

public interface BotService {

    boolean containsSignal(String str);
    ArrayList<String> getReport(String param);

}
