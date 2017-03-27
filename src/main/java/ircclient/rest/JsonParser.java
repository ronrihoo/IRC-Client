package ircclient.rest;

import org.json.JSONArray;
import org.json.JSONObject;

public interface JsonParser {

    void setJson(String str);
    String validate(String str);
    boolean isValid(String str);
    boolean isDictionary();
    void setDictionary(boolean b);
    void currentObjectIsDictionary();
    void currentObjectIsList();
    void reset();

    JsonParser gotoDict(String str);
    JsonParser gotoDict(int index);
    JsonParser gotoList(String str);
    JsonParser gotoList(int index);
    String getString(String str);
    String getString(int index);
    Double getDouble(String str);
    Double getDouble(int index);
    int getInt(String str);
    int getInt(int index);

    JSONObject getDictionary(JSONObject json, String str);
    JSONObject getDictionary(JSONArray json, int index);
    JSONArray getList(JSONObject json, String str);
    JSONArray getList(JSONArray json, int index);

    // Experimental for now -- in case they might come in handy
    String getStringFromDictionary(JSONObject json, String str);
    String getStringFromDictionary(JSONArray json, int index);
    String getStringFromList(JSONObject json, String str);
    String getStringFromList(JSONArray json, int index);


}
