package ircclient.irc.session.injector;

import ircclient.gui.AppFrame;
import ircclient.irc.session.consumer.App;
import ircclient.irc.session.consumer.Consumer;
import ircclient.util.Constants;

public class MainInjector {

    public Consumer getConsumer() {
        return new App(new AppFrame(Constants.APP_TITLE, Constants.APP_WIDTH,
                Constants.APP_HEIGHT));
    }

}
