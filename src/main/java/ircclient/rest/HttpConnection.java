package ircclient.rest;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class HttpConnection {

    HttpsURLConnection conn = null;
    OutputStreamWriter outputStream = null;
    URL request = null;
    Scanner scanner = null;
    String response = "";
    String urlStr;

    public HttpConnection() {

    }

    public HttpConnection(String urlStr) {
        this.urlStr = urlStr;
    }

    public boolean connect(String urlStr, String post) {
        OutputStream outputStream = null;
        try {
            request = new URL(urlStr);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        }
        try {
            request.openConnection().connect();
            outputStream = request.openConnection().getOutputStream();
            scanner = new Scanner(request.openConnection().getInputStream());
            outputStream.write(post.getBytes());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        System.out.println(scanner.next());
        return true;
    }

    public boolean connect(String urlStr) {
        conn = null;
        outputStream = null;
        try {
            request = new URL(urlStr);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        }
        try {
            conn = (HttpsURLConnection) request.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String sendRequest() {

        return "";
    }

    public String getResponse() {
        // using "\\Z" to cut out all the newlines to end up with single line
        response = scanner.useDelimiter("\\Z").next();  // store it
        return response;
    }

    public String getResponse(String urlStr) {
        request = null;
        scanner = null;
        response = "";
        try {
            request = new URL(urlStr);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            scanner = new Scanner(request.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // using "\\Z" to cut out all the newlines to end up with single line
        response = scanner.useDelimiter("\\Z").next();  // only one line next
        scanner.close();
        return response;
    }


    public void closeConnection() {
        scanner.close();
    }

}
