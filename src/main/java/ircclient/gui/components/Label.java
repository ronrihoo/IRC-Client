package ircclient.gui.components;

import javax.swing.*;
import java.awt.*;

public class Label {

    public static JLabel userLabel;
    public static JLabel reportLabel;
    public static JLabel serverLabel;
    public static JLabel portLabel;
    public static JLabel channelLabel;

    public Label() {
        init();
    }

    public void init() {
        Color textBackgroundColor = new Color(0x000000);
        Color textColor = new Color(0xCCCCCC);
        userLabel = setLabel("Nick", textBackgroundColor, textColor);
        reportLabel = setLabel("Bot Services", textBackgroundColor, textColor);
        serverLabel = setLabel("Server", textBackgroundColor, textColor);
        portLabel = setLabel("Port", textBackgroundColor, textColor);
        channelLabel = setLabel("Channel", textBackgroundColor, textColor);
    }

    public JLabel setLabel(String text, Color bgColor,
                         Color fontColor) {
        JLabel label = new JLabel(text);
        label.setBackground(bgColor);
        label.setForeground(fontColor);
        return label;
    }

    public JLabel setLabel(String text, Color bgColor,
                         Color fontColor, Font font) {
        JLabel label = new JLabel(text);
        label.setBackground(bgColor);
        label.setForeground(fontColor);
        label.setFont(font);
        return label;
    }

}
