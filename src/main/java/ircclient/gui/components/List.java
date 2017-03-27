package ircclient.gui.components;

import javax.swing.*;
import java.awt.*;

public class List {

    public static JList userList;
    public static DefaultListModel userListModel;

    public List() {
        init();
    }

    public void init() {
        setupUserListModel();
        setupUserList();
        initializeTheme();
    }

    public void setupUserListModel() {
        userListModel = new DefaultListModel();
    }

    public void setupUserList() {
        userList = new JList(userListModel);
        userList.setLayoutOrientation(JList.VERTICAL);
        userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        userList.setFixedCellWidth(userList.getWidth());
    }

    public void initializeTheme() {
        Color textBackgroundColor = new Color(0x000000);
        Color textColor = new Color(0xCCCCCC);
        Font textFont = new Font("Consolas", 0, 12);
        userList.setBackground(textBackgroundColor);
        userList.setForeground(textColor);
        userList.setFont(textFont);
    }

    public DefaultListModel getUserListModel() {
        return userListModel;
    }

    public JList getUserList() {
        return userList;
    }

    public void addElementToList(String element) {
        userListModel.addElement(element);
    }

    public void clearList() {
        userListModel.removeAllElements();
    }

}
