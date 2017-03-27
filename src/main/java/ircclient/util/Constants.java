package ircclient.util;

public class Constants {

    // App
    public static final String APP_TITLE = "IRC Client";
    public static final int APP_WIDTH = 826;
    public static final int APP_HEIGHT = 600;
    public static final boolean RESIZABLE = true;

    public static final String DEFAULT_NICK = "app_tester";
    public static final String DEFAULT_SERVER = "irc.freenode.net";
    public static final String DEFAULT_PORT = "6667";
    public static final String DEFAULT_CHANNEL = "#irchacks";

    // GUI
    public static final int MENU_BAR_BG_COLOR_R = 101;
    public static final int MENU_BAR_BG_COLOR_G = 101;
    public static final int MENU_BAR_BG_COLOR_B = 101;
    public static final int MENU_BAR_FONT_SIZE = 12;
    public static final int MENU_BAR_HEIGHT = 20;


    // IRC
    public static final String[] KEYWORDS = {"weather", "forecast"};

    // OpenWeatherMap

    public static final String URL_BASE = "http://api.openweathermap.org/data/2.5/weather?q=";
    public static final String URL_ZIPCODE = "?zip=";
    public static final String URL_UNITS = "&units=";
    public static final String URL_APP_ID = "&appid=";
    public static final String URL_COMMA = ",";

    public static final String REPORT_FORECAST_FOR = "Weather forecast for ";
    public static final String REPORT_CITY = " city: ";
    public static final String REPORT_SKY = " sky";
    public static final String REPORT_TEMP = " and a temperature of ";
    public static final String REPORT_HIGH = " with a high of ";
    public static final String REPORT_LOW = " and a low of ";

    public static final String UNITS_FAHRENHEIT = "imperial";
    public static final String UNITS_CELSIUS = "metric";
    public static final String UNITS_KELVIN = "default";

    public static final String DEGREES_FAHRENHEIT = "°F";
    public static final String DEGREES_CELSIUS = "°C";
    public static final String KELVIN = "K";

    public static final String DEFAULT_CITY = "Richardson";
    public static final String DEFAULT_STATE = "TX";
    public static final String DEFAULT_COUNTRY = "US";
    public static final String DEFAULT_UNITS = UNITS_FAHRENHEIT;    // imperial

    public static final String ZIPCODE_PATTERN = "(^[0-9]{5}(-?)((?:-[0-9]{4})?)*$)";
    public static final String NUMERIC_PATTERN = "^*[0-9]{5,}.*$";

    public static final String APP_ID = "6284d44ad1805afa47f2771639b0cf61";

    // Twitter API

    public static final String CONSUMER_KEY = "";
    public static final String CONSUMER_SECRET = "";
    public static final String TRENDS_API_URL = "https://api.twitter.com/1.1/trends/place.json?id=1";

}
