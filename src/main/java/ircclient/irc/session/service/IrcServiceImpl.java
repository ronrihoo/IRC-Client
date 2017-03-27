package ircclient.irc.session.service;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static ircclient.log.Report.*;

public class IrcServiceImpl implements IrcService {

    private Socket socket = null;
    private BufferedReader reader = null;
    private BufferedWriter writer = null;
    private GuiService guiService = null;
    private String server = null;
    private int port;
    private Date date = null;
    private Calendar cal = null;
    private String hour;
    private String minute;

    public IrcServiceImpl (GuiService guiService) {
        this.guiService = guiService;
    }

    private void initializeStreams() {
        try {
            writer = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            // TODO: goes in Constants
            printError("initializeStreams() issue");
            e.printStackTrace();
        }
        try {
            reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ConnectionService

    @Override
    public boolean connect(String server, int port) {
        this.server = server;
        this.port = port;
        try {
            socket = new Socket(server, port);
            guiService.printLine(connectionSuccessful(server, port));
            initializeStreams();
            return true;
        } catch (ConnectException e) {
            // TODO: goes in Constants
            printError("Connection Issue in connect(..., ...)");
            guiService.printLine(connectionRefused(server, port));
        } catch (IOException e) {
            // TODO: goes in Constants
            printError("Connection issue in connect(..., ...)");
            guiService.printLine(ioError(server, port));
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void disconnect() {

    }

    // MessageService

    @Override
    public String readLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            guiService.printLine(ioError(server, port));
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void sendLine(String line) {
        if (line != null && line.length() > 0) {
            try {
                writer.write(line);
                writer.flush();
            } catch (IOException e1) {
                // TODO: goes in Constants
                printError("sendLine() issue in ircServiceImpl...");
                e1.printStackTrace();
            }
        }
    }

    @Override
    public String getTimeStamp() {
        date = new Date();
        cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        hour = String.valueOf(cal.get(Calendar.HOUR_OF_DAY));
        minute = String.valueOf(cal.get(Calendar.MINUTE));
        if (cal.get(Calendar.HOUR_OF_DAY) < 10) {
            hour = "0" + hour;
        }
        if (cal.get(Calendar.MINUTE) < 10) {
            minute = "0" + minute;
        }
        return "[" + hour + ":" + minute + "] ";
    }
}
