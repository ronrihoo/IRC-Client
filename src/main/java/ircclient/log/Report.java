package ircclient.log;

public class Report {

    public static void println(String str) {
        System.out.println(str);
    }

    public static void printReport(String report) {
        System.out.println("Report: " + report);
    }

    public static void printError(String error) {
        System.out.println("Error: " + error);
    }

    public static void printTest(String test) {
        System.out.println("Test: " + test);
    }

    // Attention-getter
    public static void printLoudly(String loud) {
        // TODO: goes in Constants
        System.out.println("\n*****LOUD_PRINT*****: " + loud +
                " --------------------\n");
    }

    // specific reports

    public static String connectionSuccessful(String server, int port) {
        // TODO: goes in Constants
        return "Connecting to " + server + "/" + port;
    }

    // specific errors

    public static String ioError(String server, int port) {
        // TODO: goes in Constants
        return "I/O issue through connection " +
                "(" + server + ", " + port + ")";
    }

    public static String readError(String server, int port) {
        // TODO: goes in Constants
        return "Read issue through connection " +
                "(" + server + ", " + port + ")";
    }

    public static String connectionRefused(String server, int port) {
        // TODO: goes in Constants
        return "Connection refused by " + server + " at " + port;
    }

}
