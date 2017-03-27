package ircclient;

import ircclient.irc.session.consumer.BotConsumer;
import ircclient.irc.session.consumer.GuiConsumer;
import ircclient.irc.session.consumer.IrcConsumer;
import ircclient.irc.session.injector.*;
import ircclient.rest.JsonParser;
import ircclient.rest.JsonParserImpl;

import ircclient.util.Constants;
import twitterapi.TwitterApi;

public class Main {

    public static void main(String[] args) {
        init();
    }

    public static void init() {
        GuiInjector guiInjector = null;
        GuiConsumer gui = null;
        BotServiceInjector botInjector = null;
        IrcServiceInjector ircInjector = null;
        IrcConsumer app = null;
        BotConsumer[] bots = new BotConsumer[2];

        guiInjector = new AppGuiInjector();
        gui = guiInjector.getGuiConsumer();
        botInjector = new WeatherBotInjector();
        bots[0] = botInjector.getBot();
        botInjector = new TwitterBotInjector();
        bots[1] = botInjector.getBot();
        ircInjector = new FreeNodeServiceInjector();
        app = ircInjector.getConsumer(gui.getGuiServiceImpl(), bots);
        gui.setIrcClient(app.getIrcClient());
    }

}
