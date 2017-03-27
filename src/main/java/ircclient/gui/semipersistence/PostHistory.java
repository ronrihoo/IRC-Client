package ircclient.gui.semipersistence;

import java.util.ArrayList;

public class PostHistory implements PostHistoryInterface {

    private int historyIndex;
    private ArrayList<String> postHistory;

    public PostHistory(ArrayList<String> postHistory) {
        this.postHistory = postHistory;
    }

    @Override
    public void appendHistory(String str) {
        postHistory.add(str);
        historyIndex = postHistory.size();
    }

    @Override
    public String getHistory() {
        return postHistory.get(historyIndex);
    }

    @Override
    public int getHistorySize() {
        return postHistory.size();
    }

    @Override
    public void decrementHistoryIndex() {
        if (historyIndex > 0) {
            historyIndex--;
        }
    }

    @Override
    public void incrementHistoryIndex() {
        if (historyIndex < postHistory.size()) {
            historyIndex++;
        }
    }

    @Override
    public int getHistoryIndex() {
        return historyIndex;
    }

}
