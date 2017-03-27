package ircclient.irc.session.service;

import ircclient.irc.session.consumer.BotConsumer;

import java.util.ArrayList;

public class BotServiceImpl implements BotService {

    BotConsumer[] bots;
    ArrayList<String> reports;

    public BotServiceImpl(BotConsumer[] bots) {
        this.bots = bots;
        this.reports = new ArrayList<>();
    }

    @Override
    public boolean containsSignal(String str) {
        for (BotConsumer bot : bots) {
            if (bot.parseForCall(str)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<String> getReport(String str) {
        reports = new ArrayList<>();
        for (BotConsumer bot : bots) {
            if (bot.parseForCall(str)) {
                reports.add(bot.returnResult(str));
            }
        }
        return reports;
    }

}
