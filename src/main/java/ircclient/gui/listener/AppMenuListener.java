package ircclient.gui.listener;

import ircclient.gui.AppFrame;
import ircclient.gui.components.Text;
import ircclient.gui.thread.AppEvent;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AppMenuListener {

    Text text = null;
    AppFrame appFrame;
    Clipboard clipboard = null;
    Transferable currentContent = null;
    ClipboardOwner clipboardOwner = null;
    Transferable contents = null;
    String clipboardContent = null;
    boolean disablePaste;

    public AppMenuListener(AppFrame appFrame, Text text) {
        this.text = text;
        this.appFrame = appFrame;
        this.disablePaste = false;
        this.clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        try {
            this.currentContent = clipboard.getContents(null);
        } catch (Exception e) {
            disablePaste = true;
        };
        this.clipboardOwner = new ClipboardOwner() {
            @Override
            public void lostOwnership(Clipboard clipboard, Transferable transferable) {
                clipboardOwner.lostOwnership(clipboard, currentContent);
            }
        };
        this.clipboardContent = "";
    }

    // file

    public ActionListener hide() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appFrame.setStateIconified();
            }
        };
    }

    public ActionListener close() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appFrame.close();
            }
        };
    }

    // edit

    public ActionListener copyListener = e -> {
        copy();
    };

    public ActionListener pasteListener = e -> {
        paste();
    };

    public void copy() {
        Transferable transfText = new StringSelection(text.inputField.getText());
        clipboard.setContents(transfText, null);
    }

    public void copy(String str) {
        Transferable text = new StringSelection(str);
        clipboard.setContents(text, null);
    }

    public String paste() {
        contents = clipboard.getContents(null);
        try {
            clipboardContent = (String) contents.getTransferData(DataFlavor.stringFlavor);
            text.inputField.setText(text.inputField.getText().toString() + clipboardContent);
        }
        catch (UnsupportedFlavorException | IOException ex){
            System.out.println(ex);
            ex.printStackTrace();
        }
        return clipboardContent;
    }

    // help

    public ActionListener documentation() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new Doc();
            }
        };
    }

    public ActionListener about() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //About about = new About();
                //about.setVisible(true);
            }
        };
    }

    public boolean disablePaste() {
        return disablePaste;
    }

}