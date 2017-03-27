package ircclient.irc.session.injector;

import ircclient.gui.AppFrame;
import ircclient.irc.session.consumer.Gui;
import ircclient.irc.session.consumer.GuiConsumer;
import ircclient.util.Constants;

import javax.swing.*;

public class AppGuiInjector implements GuiInjector {

    @Override
    public GuiConsumer getGuiConsumer() {
        return new Gui(new AppFrame(Constants.APP_TITLE, Constants.APP_WIDTH,
                Constants.APP_HEIGHT), Constants.DEFAULT_NICK,
                Constants.DEFAULT_SERVER, Constants.DEFAULT_PORT,
                Constants.DEFAULT_CHANNEL);
    }

}
