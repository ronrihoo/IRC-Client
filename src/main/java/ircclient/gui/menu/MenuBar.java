package ircclient.gui.menu;

import ircclient.gui.AppFrame;
import ircclient.gui.listener.AppMenuListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar {

    JMenu viewMenu;
    JMenu editMenu;
    JMenu helpMenu;
    JMenuItem menuItem;
    Color bgColor;
    int menuBarHeight;
    int menuBarFontSize;
    AppMenuListener appMenuListener;

    public MenuBar(AppMenuListener appMenuListener, int appWidth,
                   int menuBarHeight, int menuBarFontSize) {
        this.appMenuListener = appMenuListener;
        this.menuBarHeight = menuBarHeight;
        this.menuBarFontSize = menuBarFontSize;
        setPreferredSize(new Dimension(
                appWidth,
                menuBarHeight
        ));
        setupViewMenu();
        setupEditMenu();
        setupHelpMenu();
        add(viewMenu);
        add(editMenu);
        add(helpMenu);
    }

    public MenuBar(AppMenuListener appMenuListener, int appWidth,
                   int menuBarHeight, int menuBarFontSize, Color bgColor,
                   Color fgColor) {
        this.appMenuListener = appMenuListener;
        this.menuBarHeight = menuBarHeight;
        this.menuBarFontSize = menuBarFontSize;
        setBackground(bgColor);
        setPreferredSize(new Dimension(
                appWidth,
                menuBarHeight
        ));
        setupViewMenu();
        setupEditMenu();
        setupHelpMenu();
        viewMenu.setBackground(bgColor);
        viewMenu.setForeground(fgColor);
        editMenu.setBackground(bgColor);
        editMenu.setForeground(fgColor);
        helpMenu.setBackground(bgColor);
        helpMenu.setForeground(fgColor);
        add(viewMenu);
        add(editMenu);
        add(helpMenu);
    }

    private void setupViewMenu() {
        viewMenu = new JMenu("File");
        viewMenu.setFont(new Font("", Font.PLAIN, menuBarFontSize));
        // hide
        menuItem = new JMenuItem("Hide");
        menuItem.addActionListener(appMenuListener.hide());
        viewMenu.add(menuItem);
        // exit
        menuItem = new JMenuItem("Exit");
        menuItem.addActionListener(appMenuListener.close());
        viewMenu.add(menuItem);
    }

    private void setupEditMenu() {
        editMenu = new JMenu("Edit");
        editMenu.setFont(new Font("", Font.PLAIN, menuBarFontSize));
        // copy
        menuItem = new JMenuItem("Copy");
        menuItem.addActionListener(appMenuListener.copyListener);
        editMenu.add(menuItem);
        // paste
        menuItem = new JMenuItem("Paste");
        menuItem.addActionListener(appMenuListener.pasteListener);
        editMenu.add(menuItem);
        if (appMenuListener.disablePaste()) {
            editMenu.getItem(1).setEnabled(false);
        }
    }

    private void setupHelpMenu() {
        helpMenu = new JMenu("Help");
        helpMenu.setFont(new Font("", Font.PLAIN, menuBarFontSize));
        // about
        menuItem = new JMenuItem("About");
        menuItem.addActionListener(appMenuListener.about());
        helpMenu.add(menuItem);
    }

    public AppMenuListener getAppMenuListener() {
        return appMenuListener;
    }

    public JMenu getEditMenu() {
        return editMenu;
    }

}