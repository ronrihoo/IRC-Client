package ircclient.gui.layout;

import ircclient.gui.components.*;
import ircclient.gui.components.Button;
import ircclient.gui.components.Label;
import ircclient.gui.components.List;
import javafx.geometry.VerticalDirection;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ScrollBarUI;
import javax.swing.plaf.ScrollPaneUI;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.synth.SynthLookAndFeel;
import java.awt.*;
import java.text.ParseException;

public class Panel {

    JPanel panel;
    JPanel inputPanel;
    JPanel controlPanel;
    JPanel ioPanel;
    JPanel userPanel;
    JScrollPane scrollPane;
    JScrollPane listScrollPane;
    JScrollBar verticalScrollBar;
    JScrollBar listVerticalScrollBar;
    RadioGroupPanel radioGroupPanel;
    Color backgroundColor;
    boolean useTheme;

    public Panel(Label label, Text text, Button button, List list) {
        useTheme = true;
        backgroundColor = new Color(0x333333);
        setupPanel(label, text, button, list);
        initializeTheme(backgroundColor);
    }

    public void initializeTheme(Color bgColor) {
        panel = applyThemeToPanel(panel, bgColor);
        inputPanel = applyThemeToPanel(inputPanel, bgColor);
        controlPanel = applyThemeToPanel(controlPanel, bgColor);
        ioPanel = applyThemeToPanel(ioPanel, bgColor);
        userPanel = applyThemeToPanel(userPanel, bgColor);
        radioGroupPanel = (RadioGroupPanel) applyThemeToPanel(radioGroupPanel, bgColor);
        for (Component component : controlPanel.getComponents()) {
            if (component.getClass() == panel.getClass()) {
                component.setBackground(bgColor);

            }
        }
    }

    public JPanel applyThemeToPanel(JPanel panel) {
        panel.setBackground(backgroundColor);
        return panel;
    }

    public JPanel applyThemeToPanel(JPanel panel, Color bgColor) {
        panel.setBackground(bgColor);
        return panel;
    }

    public JPanel getNewPanel() {
        if (useTheme) {
            return applyThemeToPanel(new JPanel());
        } else {
            return new JPanel();
        }
    }

    public void setupPanel(Label label, Text text, Button button, List list) {
        panel = new JPanel(new BorderLayout());
        setupScrollPane(text);
        setupInputPanel(text.inputField, button.postButton);
        setupControlPanel(label, text, button);
        setupListScrollPane(list);
        setupIOPanel();
        setupUserPanel();
        panel.add(ioPanel, BorderLayout.CENTER);
        panel.add(userPanel, BorderLayout.EAST);
    }

    public void setupIOPanel() {
        ioPanel = new JPanel(new BorderLayout());
        ioPanel.add(scrollPane, BorderLayout.CENTER);
        ioPanel.add(inputPanel, BorderLayout.SOUTH);
    }

    public void setupUserPanel() {
        userPanel = new JPanel(new BorderLayout());
        userPanel.add(listScrollPane, BorderLayout.CENTER);
        userPanel.add(controlPanel, BorderLayout.SOUTH);
    }

    public void setupControlPanel(Label label, Text text, Button button) {
        // distance between the control panel and its parent panel
        int d = 5;
        // radio group panel
        radioGroupPanel = new RadioGroupPanel(button);
        // set up layout for the textfield panel
        GridLayout textFieldGridLayout = new GridLayout(10, 0);
        textFieldGridLayout.setVgap(2);
        // set up textfield panel
        JPanel textFieldPanel = new JPanel(textFieldGridLayout);
        textFieldPanel.add(label.reportLabel);
        textFieldPanel.add(radioGroupPanel);
        textFieldPanel.add(label.userLabel);
        textFieldPanel.add(text.userField);
        textFieldPanel.add(label.serverLabel);
        textFieldPanel.add(text.serverField);
        textFieldPanel.add(label.portLabel);
        textFieldPanel.add(text.portField);
        textFieldPanel.add(label.channelLabel);
        textFieldPanel.add(text.channelField);
        // set up button panel
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
        buttonPanel.add(getNewPanel());
        buttonPanel.add(button.connect, BorderLayout.EAST);
        // set up control panel
        controlPanel = new JPanel(new BorderLayout());
        controlPanel.setBorder(BorderFactory.createEmptyBorder(d, d, d, d));
        controlPanel.setPreferredSize(new Dimension(150, 270));
        controlPanel.add(textFieldPanel, BorderLayout.CENTER);
        controlPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void setupInputPanel(JTextField textField, JButton postButton) {
        inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(textField, BorderLayout.CENTER);
        inputPanel.add(postButton, BorderLayout.EAST);
    }

    public BasicScrollBarUI scrollBarUI(final Color themeThumbColor) {

        return new BasicScrollBarUI() {

            JButton jButton;
            Dimension noDimension = new Dimension(0, 0);;
            Color defaultThumbColor = new Color(0x111111);

            private JButton noButton() {
                jButton = new JButton();
                jButton.setPreferredSize(noDimension);
                jButton.setMaximumSize(noDimension);
                jButton.setMinimumSize(noDimension);
                return jButton;
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return noButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return noButton();
            }

            @Override
            protected void configureScrollBarColors(){
                if (themeThumbColor != null) {
                    this.thumbColor = themeThumbColor;
                } else {
                    this.thumbColor = defaultThumbColor;
                }
            }

        };

    }

    public LineBorder scrollBarBorder() {
        return new LineBorder(
                Color.LIGHT_GRAY,
                1,
                true
        );
    }

    public void setupScrollPane(Text text) {
        scrollPane = new JScrollPane(text.textArea);
        scrollPane.setPreferredSize(new Dimension(650, 440));
        verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setBackground(new Color(0x333333));
        verticalScrollBar.setBorder(scrollBarBorder());
        verticalScrollBar.setUI(scrollBarUI(null));
    }

    public void setupListScrollPane(List list) {
        listScrollPane = new JScrollPane(list.userList);
        listScrollPane.setPreferredSize(new Dimension(150, 440));
        listVerticalScrollBar = listScrollPane.getVerticalScrollBar();
        listVerticalScrollBar.setBackground(new Color(0x333333));
        listVerticalScrollBar.setBorder(scrollBarBorder());
        listVerticalScrollBar.setUI(scrollBarUI(null));

    }

    public void updateScrollBar() {
        verticalScrollBar.setValue(verticalScrollBar.getMaximum());
    }

    public void updateListScrollBar() {
        listVerticalScrollBar.setValue(listVerticalScrollBar.getMaximum());
    }

    public JPanel getPanel() {
        return panel;
    }

}
