package ircclient.gui.components;


import ircclient.gui.listener.AppKeyListener;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.html.HTMLDocument;
import java.awt.*;
import java.io.IOException;

public class Text {

    public JTextArea textArea;
    public JTextField inputField;
    public JTextField userField;
    public JTextField serverField;
    public JTextField portField;
    public JTextField channelField;
    //public JTextField passField;
    //public JTextField realNameField;

    public Text() {
        init();
    }

    public void init() {
        setupTextArea();
        setupInputField();
        setupControlFields();
    }

    public void initializeTheme() {
        Color backgroundColor = new Color(0x000000);
        Color textColor = new Color(0xCCCCCC);
        Font font = new Font("Consolas", 0, 12);
        initializeComponentsWithTheme(backgroundColor, textColor, font);
    }

    public void initializeComponentsWithTheme(Color bgColor,
                                              Color textColor,
                                              Font font) {
        textArea = applyThemeToTextArea(textArea, bgColor, textColor, font);
        inputField = applyThemeToTextField(inputField, bgColor, textColor, font);
        userField = applyThemeToTextField(userField, bgColor, textColor, font);
        serverField = applyThemeToTextField(serverField, bgColor, textColor, font);
        portField = applyThemeToTextField(portField, bgColor, textColor, font);
        channelField = applyThemeToTextField(channelField, bgColor, textColor, font);
    }

    public JTextArea applyThemeToTextArea(JTextArea jTextArea, Color bgColor,
                                          Color textColor, Font font) {
        jTextArea.setBackground(bgColor);
        jTextArea.setForeground(textColor);
        jTextArea.setFont(font);
        return textArea;
    }

    public JTextField applyThemeToTextField(JTextField jTextField, Color bgColor,
                                            Color textColor, Font font) {
        jTextField.setBackground(bgColor);
        jTextField.setForeground(textColor);
        jTextField.setFont(font);
        return jTextField;
    }

    public void setupTextArea() {
        textArea = new JTextArea();
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
    }

    public void setupInputField() {
        inputField = new JTextField("");
        inputField.setPreferredSize(new Dimension(700, 30));
    }

    public void setupControlFields() {
        userField = new JTextField("");
        serverField = new JTextField("");
        portField = new JTextField("");
        channelField = new JTextField("");
    }

    public void updateTextAreaCaretPos() {
        textArea.setCaretPosition(textArea
                .getDocument()
                .getLength()
        );
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public String getTextAreaContents() {
        return textArea.getText();
    }

    public void setTextAreaContents(String contents) {
        textArea.setText(contents);
        updateTextAreaCaretPos();
    }

    public void appendToTextArea(String contents) {
        //textArea.setText(textArea.getText() + contents + "\n");
        textArea.append(contents + "\n");
        updateTextAreaCaretPos();
    }

    public void setupAppKeyListeners(AppKeyListener appKeyListener) {
        inputField.addKeyListener(appKeyListener);
    }

    public String getInputFieldText() {
        return inputField.getText();
    }

    public void clearInputField() {
        inputField.setText("");
    }

    public String getUserFieldText() { return userField.getText(); }

    public String getServerFieldText() {
        return serverField.getText();
    }

    public String getPortFieldText() {
        return portField.getText();
    }

    public String getChannelFieldText() {
        return channelField.getText();
    }

    public JTextField getUserField() { return userField; }

    public JTextField getChannelField() {
        return channelField;
    }

    public JTextField getPortField() {
        return portField;
    }

    public JTextField getServerField() {
        return serverField;
    }

}
