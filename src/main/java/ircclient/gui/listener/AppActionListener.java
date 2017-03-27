package ircclient.gui.listener;

import ircclient.gui.components.Button;
import ircclient.gui.components.Text;
import ircclient.gui.thread.AppEvent;
import ircclient.irc.session.consumer.IrcConsumer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppActionListener implements ActionListener {

    Button button = null;
    AppEvent appEvent;

    public AppActionListener(Button button, AppEvent appEvent) {
        this.button = button;
        this.appEvent = appEvent;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button.getPostButton()) {
            appEvent.postButtonEvent();
        } else if (e.getSource() == button.getConnectButton()) {
            if (button.getConnectButton().getText().equals("Connect")) {
                // connect to server, login, and join channel
                appEvent.connectButtonEvent();
            } else {
                // update button
                appEvent.disconnectButtonEvent();
            }
        }
    }

}
