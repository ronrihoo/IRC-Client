package ircclient.gui.thread;

import ircclient.gui.components.Button;
import ircclient.gui.components.Text;
import ircclient.irc.session.consumer.IrcClient;
import ircclient.irc.session.consumer.IrcConsumer;

public class AppEvent {

    Button button = null;
    Text text = null;
    IrcConsumer ircClient = null;

    public AppEvent(Button button, Text text) {
        this.button = button;
        this.text = text;
    }

    public void setIrcClient(IrcConsumer ircClient) {
        this.ircClient = ircClient;
    }

    Runnable runPostEvent = new Runnable() {
        public void run() {
            ircClient.post(text.getInputFieldText());
            text.clearInputField();
        }
    };

    Runnable runConnectionEvent = new Runnable() {
        public void run() {
            if (text.getUserFieldText().length() > 0) {
                ircClient.setNick(text.getUserFieldText());
            } else {
                // TODO: error messages go in the Constants class
                ircClient.print("Error: no nick has been provided.");
            }
            if (text.getServerFieldText().length() > 0) {
                ircClient.setServer(text.getServerFieldText());
            } else {
                ircClient.print("Error: no server info has been provided.");
            }
            if (text.getPortFieldText().length() > 0) {
                ircClient.setPort(Integer.parseInt(text.getPortFieldText()));
            } else {
                ircClient.print("Error: no port has been provided.");
            }
            if (text.getChannelFieldText().length() > 0) {
                ircClient.setChannel(text.getChannelFieldText());
            } else {
                ircClient.print("Error: no channel has been provided.");
            }
            if (text.getServerFieldText().length() > 0 &&
                    text.getPortFieldText().length() > 0 &&
                    text.getChannelFieldText().length() > 0 &&
                    text.getUserFieldText().length() > 0) {
                // TODO: get this mechanism out of here. Is it even still needed?
                // update button
                button.getConnectButton().setEnabled(false);
                // connect
                ircClient.connect(text.getServerFieldText(),
                        Integer.parseInt(text.getPortFieldText()));
            }
        }
    };

    Runnable runDisconnectionEvent = new Runnable() {
        public void run() {
            ircClient.leave();
            ircClient.disconnect();
        }
    };

    public boolean postButtonEvent() {
        if (button.getPostButton().isEnabled() &&
                text.getInputFieldText().length() > 0) {
            new Thread(runPostEvent).start();
            return true;
        }
        return false;
    }

    public void connectButtonEvent() {
        new Thread(runConnectionEvent).start();
    }

    public void disconnectButtonEvent() {
        new Thread(runDisconnectionEvent).start();
    }

}
