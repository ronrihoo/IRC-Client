package ircclient.irc.session.injector;

import ircclient.irc.session.consumer.BotConsumer;
import ircclient.irc.session.consumer.IrcConsumer;
import ircclient.irc.session.consumer.FreeNodeClient;
import ircclient.irc.session.service.*;
import ircclient.irc.session.service.freenode.FreeNode;

public class FreeNodeServiceInjector implements IrcServiceInjector {

    @Override
    public IrcConsumer getConsumer(GuiServiceImpl guiService,
                                   BotConsumer[] bots) {
        return new FreeNodeClient(
                new IrcServiceImpl(guiService),
                guiService,
                new BotServiceImpl(bots),
                new FreeNode(guiService.getNick(), guiService.getChannel()));
    }

}
