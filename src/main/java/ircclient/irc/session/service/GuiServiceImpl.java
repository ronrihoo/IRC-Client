package ircclient.irc.session.service;

import ircclient.gui.components.Button;
import ircclient.gui.components.List;
import ircclient.gui.components.Text;

import java.util.ArrayList;

public class GuiServiceImpl implements GuiService {

    Text outputComponents;
    List listComponents;
    Button buttonComponents;

    public GuiServiceImpl(Text outputComponents, List listComponents,
                          Button buttonComponents) {
        this.outputComponents = outputComponents;
        this.listComponents = listComponents;
        this.buttonComponents = buttonComponents;
    }

    @Override
    public void append(String text) {
        outputComponents.appendToTextArea(text);
    }

    @Override
    public void setText(String text) {
        outputComponents.setTextAreaContents(text);
    }

    @Override
    public String getText() {
        return outputComponents.getTextAreaContents();
    }

    @Override
    public String getNick() {
        return outputComponents.getUserFieldText();
    }

    @Override
    public String getChannel() {
        return outputComponents.getChannelFieldText();
    }

    @Override
    public void printLine(String line) {
        append(line);
    }

    @Override
    public void updateUserList(ArrayList<String> users) {
        listComponents.clearList();
        for (String user : users) {
            listComponents.addElementToList(user);
        }
    }

    @Override
    public void updateConnectionButton(boolean state) {
        buttonComponents.setConnectionButtonLabel(state);
        enableConnectionButton(!state);
    }

    @Override
    public void enableConnectionButton(boolean enable) {
        buttonComponents.enableConnectionButton(enable);
    }

    @Override
    public void setConnectButton() {
        buttonComponents.setConnectButton();
    }

    @Override
    public void setDisconnectButton() {
        buttonComponents.setDisonnectButton();
    }

    @Override
    public void enablePostButton() {
        buttonComponents.enablePostButton();
    }

    @Override
    public void disablePostButton() {
        buttonComponents.disablePostButton();
    }

    @Override
    public boolean botIsEnabled() {
        return isBotEnabled();
    }

    @Override
    public boolean isBotEnabled() {
        return buttonComponents.getRadioButton(0).isSelected();
    }



}
