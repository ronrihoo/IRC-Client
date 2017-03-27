package ircclient.irc.session.consumer;

import ircclient.gui.AppFrame;
import ircclient.irc.session.service.GuiServiceImpl;

public class Gui implements GuiConsumer {

    AppFrame appFrame;
    IrcConsumer ircClient;

    public Gui(AppFrame appFrame) {
        this.appFrame = appFrame;
        init();
    }

    public Gui(AppFrame appFrame, String defaultNick, String defaultServer,
               String defaultPort, String defaultChannel) {
        this.appFrame = appFrame;
        appFrame.setupAppListeners();
        appFrame.getText().getUserField().setText(defaultNick);
        appFrame.getText().getServerField().setText(defaultServer);
        appFrame.getText().getPortField().setText(defaultPort);
        appFrame.getText().getChannelField().setText(defaultChannel);
    }

    public void init(){
        appFrame.setupAppListeners();
        appFrame.getText().getUserField().setText("app_tester");
        appFrame.getText().getServerField().setText("irc.freenode.net");
        appFrame.getText().getPortField().setText("6667");
        appFrame.getText().getChannelField().setText("#irchacks");
    }

    @Override
    public AppFrame getAppFrame() {
        return appFrame;
    }

    @Override
    public GuiServiceImpl getGuiServiceImpl() {
        return new GuiServiceImpl(appFrame.getText(), appFrame.getList(),
                appFrame.getButton());
    }

    @Override
    public void setIrcClient(IrcConsumer ircClient) {
        this.ircClient = ircClient;
        appFrame.getAppEvent().setIrcClient(ircClient);
    }


}
