package ircclient.irc.session.injector;

import ircclient.irc.session.consumer.BotConsumer;
import ircclient.irc.session.consumer.IrcConsumer;
import ircclient.irc.session.consumer.IrcClient;
import ircclient.irc.session.service.*;

public class DefaultIrcInjector implements IrcServiceInjector {

    @Override
    public IrcConsumer getConsumer(GuiServiceImpl guiService,
                                   BotConsumer[] bots) {
        return new IrcClient(new IrcServiceImpl(guiService), guiService,
                new BotServiceImpl(bots));
    }

}
