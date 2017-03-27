package ircclient.gui.layout;

import javax.swing.*;
import java.awt.*;

import ircclient.gui.components.Button;

public class RadioGroupPanel extends JPanel {

    boolean useTheme;
    Color backgroundColor;

    public RadioGroupPanel(Button button) {
        setupRadioGroupPanel(button);
    }

    public void setupRadioGroupPanel(Button button) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 20));
        add(button.getRadioButton(0), BorderLayout.WEST);
        add(button.getRadioButton(1), BorderLayout.EAST);
    }

    public boolean isUseTheme() {
        return useTheme;
    }

    public void setUseTheme(boolean useTheme) {
        this.useTheme = useTheme;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

}
