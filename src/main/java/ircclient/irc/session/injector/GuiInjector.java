package ircclient.irc.session.injector;

import ircclient.gui.AppFrame;
import ircclient.irc.session.consumer.GuiConsumer;

public interface GuiInjector {

    GuiConsumer getGuiConsumer();

}
