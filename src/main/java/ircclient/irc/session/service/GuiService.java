package ircclient.irc.session.service;

import java.util.ArrayList;

public interface GuiService {

    String getText();
    String getNick();
    String getChannel();
    void append(String str);
    void setText(String str);
    void printLine(String line);
    void updateUserList(ArrayList<String> users);
    void updateConnectionButton(boolean connectionStatus);
    void enableConnectionButton(boolean enable);
    void setConnectButton();
    void setDisconnectButton();
    void enablePostButton();
    void disablePostButton();
    boolean botIsEnabled();
    boolean isBotEnabled();

}
