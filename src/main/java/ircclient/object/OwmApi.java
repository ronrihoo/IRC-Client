package ircclient.object;

import ircclient.util.Constants;

// OpenWeatherMap API Call -- URL builder
public class OwmApi {

    private String city;
    private String country;  // map country - state/country
    private String zipcode;
    private String units;
    private String appId;

    public OwmApi(String zipcode) {
        this.city = "";
        this.country = Constants.DEFAULT_COUNTRY;
        this.zipcode = zipcode;
        this.units = Constants.DEFAULT_UNITS;
        this.appId = Constants.APP_ID;
    }

    public OwmApi(String city, String country) {
        this.city = city;
        this.country = country;
        this.units = Constants.DEFAULT_UNITS;
        this.appId = Constants.APP_ID;
    }

    public OwmApi(String city, String country, String units) {
        this.city = city;
        this.country = country;
        this.units = units;
        this.appId = Constants.APP_ID;
    }

    public String getUrl() {
        return Constants.URL_BASE +
                city + "," + country +
                Constants.URL_UNITS + units +
                Constants.URL_APP_ID + appId;
    }

    public String getByZipCodeUrl() {
        return Constants.URL_BASE +
                Constants.URL_ZIPCODE + zipcode +
                Constants.URL_COMMA + country +
                Constants.URL_UNITS + units +
                Constants.URL_APP_ID + appId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getcountry() {
        return country;
    }

    public void setcountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipcode;
    }

    public void setZipCode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
