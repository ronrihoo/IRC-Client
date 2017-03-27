package ircclient.irc.session.injector;

import ircclient.irc.session.consumer.BotConsumer;
import ircclient.irc.session.consumer.TwitterBot;
import ircclient.util.Constants;
import twitterapi.TwitterApi;

public class TwitterBotInjector implements BotServiceInjector {

    @Override
    public BotConsumer getBot() {
        return new TwitterBot(new TwitterApi(Constants.CONSUMER_KEY,
                Constants.CONSUMER_SECRET));
    }

}
