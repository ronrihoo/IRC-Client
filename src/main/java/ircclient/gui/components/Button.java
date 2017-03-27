package ircclient.gui.components;

import ircclient.gui.listener.AppActionListener;
import ircclient.gui.listener.AppKeyListener;

import javax.swing.*;
import java.awt.*;

public class Button {

    public static JButton postButton;
    public static JButton connect;
    public static JRadioButton[] radioButtons;
    public static ButtonGroup radioGroup;

    public Button() {
        init();
    }

    public static void init() {
        setupPostButton();
        setupConnectButton();
        setupRadioButtons();
        setupRadioGroup();
    }

    public void applyTheme(Color bgColor, Color fgColor,
                           Color buttonBgColor, Color buttonFgColor) {
        for (JRadioButton radioButton : radioButtons) {
            radioButton.setBackground(bgColor);
            radioButton.setForeground(fgColor);
        }
        postButton = applyThemeToButton(postButton, buttonBgColor, buttonFgColor);
        connect = applyThemeToButton(connect, buttonBgColor, buttonFgColor);
    }

    public JButton applyThemeToButton(JButton jButton, Color bgColor, Color fgColor) {
        jButton.setBackground(bgColor);
        jButton.setForeground(fgColor);
        return jButton;
    }

    public static void setupPostButton() {
        postButton = new JButton("POST");
    }

    public static void setupConnectButton() {
        connect = new JButton("Connect");
        //connect.addActionListener(Config.appFrameContext.appActionListener);
    }

    public void setConnectButton() {
        connect.setText("Connect");
        connect.setEnabled(true);
    }

    public static void setDisonnectButton() {
        connect.setText("Disconnect");
        connect.setEnabled(true);
    }

    public static void setupRadioButtons() {
        radioButtons = new JRadioButton[2];
        radioButtons[0] = new JRadioButton("on");
        radioButtons[1] = new JRadioButton("off");
        // initial selected button
        radioButtons[0].setSelected(true);
    }

    public static void setupRadioGroup() {
        radioGroup = new ButtonGroup();
        radioGroup.add(radioButtons[0]);
        radioGroup.add(radioButtons[1]);
    }

    public JRadioButton getRadioButton(int i) {
        return radioButtons[i];
    }

    public ButtonGroup getRadioGroup() {
        return radioGroup;
    }

    public void setConnectionButtonLabel(boolean state) {
        if (state) {
            connect.setText("Connect");
        } else {
            connect.setText("Disconnect");
        }
    }

    public void enableConnectionButton(boolean enable) {
        connect.setEnabled(enable);
    }

    public void disableConnectionButton() {
        connect.setEnabled(false);
    }

    public void enablePostButton() {
        postButton.setEnabled(true);
    }

    public void disablePostButton() {
        postButton.setEnabled(false);
    }

    public JButton getConnectButton() {
        return connect;
    }

    public JButton getPostButton() {
        return postButton;
    }

    public void setupActionListeners(AppActionListener appActionListener) {
        postButton.addActionListener(appActionListener);
        connect.addActionListener(appActionListener);
    }

}
