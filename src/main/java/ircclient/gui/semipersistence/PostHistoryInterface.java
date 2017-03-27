package ircclient.gui.semipersistence;

public interface PostHistoryInterface {

    void appendHistory(String str);
    void incrementHistoryIndex();
    void decrementHistoryIndex();
    String getHistory();
    int getHistorySize();
    int getHistoryIndex();


}
