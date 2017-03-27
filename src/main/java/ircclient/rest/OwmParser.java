package ircclient.rest;

import ircclient.object.Weather;

import org.json.JSONObject;

public class OwmParser {

    JsonParser jsonParser = null;
    Weather weather = null;

    public OwmParser(JsonParser jsonParser) {
        this.jsonParser = jsonParser;
    }

    public Weather parseWeatherInfo(String apiResponse) {
        jsonParser.setJson(apiResponse);
        weather = new Weather();
        // valid: return null; invalid: return str which contains error
        if (jsonParser.validate(apiResponse) == null) {
            // sky description
            weather.setSky_description(
                    jsonParser.gotoList("weather").gotoDict(0).getString("main")
            );
            // temp, low, high, pressure
            jsonParser.reset();
            weather.setTemp(
                    jsonParser.gotoDict("main").getDouble("temp")
            );
            jsonParser.reset();
            weather.setTemp_high(
                    jsonParser.gotoDict("main").getDouble("temp_max")
            );
            jsonParser.reset();
            weather.setTemp_low(
                    jsonParser.gotoDict("main").getDouble("temp_min")
            );
            jsonParser.reset();
            weather.setPressure(
                    jsonParser.gotoDict("main").getDouble("pressure")
            );
            // city, state, country (sanity check)
            jsonParser.reset();
            weather.setCity(
                    jsonParser.getString("name")
            );
            jsonParser.reset();
            weather.setState("");   // OpenWeatherMap does not provide this info yet
            weather.setCountry(
                    jsonParser.gotoDict("sys").getString("country")
            );
        } else {
            // TODO: use DI for logging/error-reporting
            System.out.println("Error: JsonParserImpl: " + apiResponse);
        }
        // will return null when there's an error
        return weather;
    }

}
