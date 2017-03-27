package ircclient.gui;

import ircclient.gui.components.Button;
import ircclient.gui.components.Label;
import ircclient.gui.components.List;
import ircclient.gui.components.Text;
import ircclient.gui.layout.Panel;
import ircclient.gui.listener.AppActionListener;
import ircclient.gui.listener.AppKeyListener;
import ircclient.gui.listener.AppMenuListener;
import ircclient.gui.semipersistence.PostHistory;
import ircclient.gui.thread.AppEvent;
import ircclient.gui.menu.MenuBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class AppFrame extends JFrame {

    // App specs
    String appTitle = null;
    int appWidth;
    int appHeight;

    // GUI components
    Button button;
    Label label;
    List list;
    Text text;
    Panel panel;
    MenuBar menuBar;

    // threads
    AppEvent appEvent;

    // listener
    AppActionListener appActionListener;
    AppKeyListener appKeyListener;
    AppMenuListener appMenuListener;

    // semipersistence
    PostHistory postHistory;

    public AppFrame(String appTitle, int appWidth, int appHeight) {
        this.appTitle = appTitle;
        this.appWidth = appWidth;
        this.appHeight = appHeight;
        setTitle(appTitle);
        setSize(appWidth, appHeight);
        //setResizable(Constants.RESIZABLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Menu
        //...
        // set up components
        initializeGuiComponents();
        // thread
        initializeEvents(button, text);
        // semipersistence
        initializePersistenceModules();
        // listener
        initializeListeners(this.button, this.text);
        // menu
        initializeMenu(new Color(0x222222), new Color(0xCCCCCC));
        setJMenuBar(menuBar);
        // add to frame
        add(panel.getPanel());
        // show frame
        setVisible(true);
    }

    public void initializeGuiComponents() {
        Color bgColor = new Color(0x333333);
        Color fgColor = new Color(0xCCCCCC);
        Color buttonBgColor = new Color(0x121212);  // or 0x222222 (lighter)
        Color buttonFgColor = new Color(0xCCCCCC);
        button = new Button();
        button.getPostButton().setEnabled(false);
        button.applyTheme(bgColor, fgColor, buttonBgColor, buttonFgColor);
        label = new Label();
        list = new List();
        text = new Text();
        text.initializeTheme();
        panel = new Panel(label, text, button, list);
    }

    public void initializeMenu() {
        menuBar = new MenuBar(appMenuListener, appWidth, 20, 12);
    }

    public void initializeMenu(Color menuBgColor, Color menuFgColor) {
        menuBar = new MenuBar(appMenuListener, appWidth, 20, 12,
                menuBgColor, menuFgColor);
    }

    public void initializeEvents(Button button, Text text) {
        appEvent = new AppEvent(button, text);
    }

    public void initializePersistenceModules() {
        // TODO: this ArrayList<String> should come from where it's changed
        // local placement of the parameter will do for now
        postHistory = new PostHistory(new ArrayList<>());
    }

    public void initializeListeners(Button button, Text text) {
        appActionListener = new AppActionListener(button, appEvent);
        appKeyListener = new AppKeyListener(text, postHistory, appEvent);
        appMenuListener = new AppMenuListener(this, text);
    }


    public void close() {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    public Button getButton() {
        return button;
    }

    public Label getLabel() {
        return label;
    }

    public List getList() {
        return list;
    }

    public Text getText() {
        return text;
    }

    public Panel getPanel() {
        return panel;
    }

    public PostHistory getPostHistory() {
        return postHistory;
    }

    public AppEvent getAppEvent() {
        return appEvent;
    }

    public AppActionListener getAppActionListener() {
        return appActionListener;
    }

    public AppKeyListener getAppKeyListener() {
        return appKeyListener;
    }

    public AppMenuListener getAppMenuListener() {
        return getAppMenuListener();
    }

    public void setupAppListeners() {
        button.setupActionListeners(this.appActionListener);
        text.setupAppKeyListeners(this.appKeyListener);
    }

    public void setStateIconified() {
        setState(Frame.ICONIFIED);
    }

    public void closeApp() {
        close();
    }

}
