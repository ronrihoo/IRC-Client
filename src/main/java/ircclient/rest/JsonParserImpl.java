package ircclient.rest;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonParserImpl implements JsonParser {

    JSONObject jsonObject = null;           // {}
    JSONArray jsonArray = null;             // []
    String jsonStr = null;
    boolean dictionary;

    public JsonParserImpl() {

    }

    public JsonParserImpl(String str) {
        this.jsonObject = new JSONObject(str);
        this.jsonStr = str;
        this.dictionary = true;
    }

    public void printError(String error) {
        System.out.println("Error: " + error);
    }

    public void printDictionaryParamTypeError() {
        printError("attempted to access a dictionary object with " +
                "incorrect key type. Must provide a String for argument");
    }

    public void printListParamTypeError() {
        printError("attempted to access a dictionary object with " +
                "incorrect key type. Must provide a String for argument");
    }

    @Override
    public void setJson(String str) {
        jsonStr = str;
        reset();
    }

    @Override
    public String validate(String str) {
        // TODO: error message strings go in Constants.java
        if (str.contains("Error: received HTTP Status: ") ||
                str.contains("Error: there has been a connection problem.")) {
            return str;
        } else {
            return null;
        }
    }

    @Override
    public boolean isValid(String str) {
        // TODO: error message strings go in Constants.java
        if (str.contains("Error: received HTTP Status: ") ||
                str.contains("Error: there has been a connection problem.")) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void setDictionary(boolean b) {
        this.dictionary = b;
    }

    @Override
    public void currentObjectIsDictionary() {
        setDictionary(true);
    }

    @Override
    public void currentObjectIsList() {
        setDictionary(false);
    }

    @Override
    public void reset() {
        jsonObject = new JSONObject(jsonStr);
        currentObjectIsDictionary();
    }

    @Override
    public boolean isDictionary() {
        return dictionary;
    }

    @Override
    public JsonParser gotoDict(String str) {
        if (isDictionary()) {
            jsonObject = jsonObject.getJSONObject(str);
            currentObjectIsDictionary();
        } else {
            printListParamTypeError();
        }
        return this;
    }

    @Override
    public JsonParser gotoDict(int index) {
        if (isDictionary()) {
            printDictionaryParamTypeError();
        } else {
            jsonObject = jsonArray.getJSONObject(index);
            currentObjectIsDictionary();
        }
        return this;
    }

    @Override
    public JsonParser gotoList(String str) {
        if (isDictionary()) {
            jsonArray = jsonObject.getJSONArray(str);
            currentObjectIsList();
        } else {
            printListParamTypeError();
        }
        return this;
    }

    @Override
    public JsonParser gotoList(int index) {
        if (isDictionary()) {
            printDictionaryParamTypeError();
        } else {
            jsonArray = jsonArray.getJSONArray(index);
            currentObjectIsList();
        }
        return this;
    }

    @Override
    public String getString(String str) {
        if (isDictionary()) {
            return jsonObject.getString(str);
        } else {
            printListParamTypeError();
            return null;
        }
    }

    @Override
    public String getString(int index) {
        if (isDictionary()) {
            printDictionaryParamTypeError();
            return null;
        } else {
            return jsonArray.getString(index);
        }
    }

    @Override
    public Double getDouble(String str) {
        if (isDictionary()) {
            return jsonObject.getDouble(str);
        } else {
            printListParamTypeError();
            return null;
        }
    }

    @Override
    public Double getDouble(int index) {
        if (isDictionary()) {
            printDictionaryParamTypeError();
            return null;
        } else {
            return jsonArray.getDouble(index);
        }
    }

    @Override
    public int getInt(String str) {
        if (isDictionary()) {
            return jsonObject.getInt(str);
        } else {
            printListParamTypeError();
            return 0;
        }
    }

    @Override
    public int getInt(int index) {
        if (isDictionary()) {
            printDictionaryParamTypeError();
            return 0;
        } else {
            return jsonArray.getInt(index);
        }
    }

    @Override
    public JSONObject getDictionary(JSONObject json, String str) {
        return json.getJSONObject(str);
    }

    @Override
    public JSONObject getDictionary(JSONArray json, int index) {
        return json.getJSONObject(index);
    }

    @Override
    public JSONArray getList(JSONObject json, String str) {
        return json.getJSONArray(str);
    }

    @Override
    public JSONArray getList(JSONArray json, int index) {
        return json.getJSONArray(index);
    }

    @Override
    public String getStringFromDictionary(JSONObject json, String str) {
        return json.getString(str);
    }

    @Override
    public String getStringFromDictionary(JSONArray json, int index) {
        return json.getString(index);
    }

    @Override
    public String getStringFromList(JSONObject json, String str) {
        return json.getString(str);
    }

    @Override
    public String getStringFromList(JSONArray json, int index) {
        return json.getString(index);
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public JSONArray getJsonArray() {
        return jsonArray;
    }

    public void setJsonArray(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

}
