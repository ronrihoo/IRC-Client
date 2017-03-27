package ircclient.irc.session.consumer;

import ircclient.gui.AppFrame;
import ircclient.irc.session.service.GuiServiceImpl;

public interface GuiConsumer {

    AppFrame getAppFrame();
    GuiServiceImpl getGuiServiceImpl();
    void setIrcClient(IrcConsumer ircClient);

}
