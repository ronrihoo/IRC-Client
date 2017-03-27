package ircclient.gui.listener;

import ircclient.gui.components.Text;
import ircclient.gui.semipersistence.PostHistory;
import ircclient.gui.semipersistence.PostHistoryInterface;
import ircclient.gui.thread.AppEvent;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AppKeyListener  implements KeyListener {

    Text text = null;
    PostHistoryInterface postHistory = null;
    AppEvent appEvent = null;

    public AppKeyListener(Text text, PostHistory postHistory,
                          AppEvent appEvent) {
        this.text = text;
        this.postHistory = postHistory;
        this.appEvent = appEvent;
    }

    @Override
    public void keyTyped(KeyEvent k) {

    }

    @Override
    public void keyPressed(KeyEvent k) {
        if(k.getKeyCode() == KeyEvent.VK_ENTER) {
            // postButtonEvent() returns true if the postButton is enabled
            if (appEvent.postButtonEvent()) {
                postHistory.appendHistory(text.inputField.getText());
            }
        } else if(k.getKeyCode() == KeyEvent.VK_UP) {
            if (postHistory.getHistorySize() > 0) {
                postHistory.decrementHistoryIndex();
                text.inputField.setText(postHistory.getHistory());
            }
        } else if(k.getKeyCode() == KeyEvent.VK_DOWN) {
            if (postHistory.getHistorySize() > 0 &&
                    postHistory.getHistoryIndex() <=
                            postHistory.getHistorySize()) {
                postHistory.incrementHistoryIndex();
                if (postHistory.getHistoryIndex()
                        < postHistory.getHistorySize()) {
                    text.inputField.setText(postHistory.getHistory());
                } else {
                    text.inputField.setText("");
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent k) {

    }

}
