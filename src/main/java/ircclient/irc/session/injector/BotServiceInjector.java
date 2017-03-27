package ircclient.irc.session.injector;

import ircclient.irc.session.consumer.BotConsumer;

public interface BotServiceInjector {

    BotConsumer getBot();

}
