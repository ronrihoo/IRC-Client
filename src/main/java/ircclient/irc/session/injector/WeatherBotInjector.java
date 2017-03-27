package ircclient.irc.session.injector;

import ircclient.irc.session.consumer.BotConsumer;
import ircclient.irc.session.consumer.WeatherBot;
import ircclient.rest.HttpConnection;
import ircclient.rest.JsonParserImpl;
import ircclient.rest.OwmParser;
import ircclient.rest.Regex;
import ircclient.util.Constants;

public class WeatherBotInjector implements BotServiceInjector {

    @Override
    public BotConsumer getBot() {
        return new WeatherBot(new HttpConnection(), new OwmParser(new JsonParserImpl()),
                new Regex(Constants.NUMERIC_PATTERN, Constants.ZIPCODE_PATTERN));
    }

}
