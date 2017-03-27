package ircclient.irc.session.injector;

import ircclient.irc.session.consumer.BotConsumer;
import ircclient.irc.session.consumer.IrcConsumer;
import ircclient.irc.session.service.GuiServiceImpl;

public interface IrcServiceInjector {

    IrcConsumer getConsumer(GuiServiceImpl outputArea, BotConsumer[] bots);

}
