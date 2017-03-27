package ircclient.irc.session.consumer;

import ircclient.object.OwmApi;
import ircclient.rest.HttpConnection;
import ircclient.rest.OwmParser;
import ircclient.rest.Regex;

public class WeatherBot implements BotConsumer {

    HttpConnection httpConnection = null;
    OwmParser jsonParser = null;
    OwmApi url = null;
    Regex regex;

    public WeatherBot(HttpConnection httpConnection, OwmParser jsonParser,
                      Regex regex) {
        this.httpConnection = httpConnection;
        this.jsonParser = jsonParser;
        this.regex = regex;
    }

    @Override
    public boolean parseForCall(String str) {
        if ((str.toLowerCase().contains("weather") ||
                str.toLowerCase().contains("forecast"))) {
            return true;
        }
        return false;
    }

    @Override
    public String validateInput(String str) {
        if (regex.testZipCode(str).length() >= 5) {
            return regex.testZipCode(str);
        } else {
            return null;
        }
    }

    @Override
    public String makeApiCall(String str) {
        str = validateInput(str);
        if (str != null) {
            url = new OwmApi(str);
            return httpConnection.getResponse(url.getByZipCodeUrl());
        }
        return null;
    }

    @Override
    public String parseJsonContents(String str) {
        return jsonParser.parseWeatherInfo(str).report();
    }

    @Override
    public String returnResult(String str) {
        str = makeApiCall(str);
        if (str != null) {
            return parseJsonContents(str);
        }
        return null;
    }

}
