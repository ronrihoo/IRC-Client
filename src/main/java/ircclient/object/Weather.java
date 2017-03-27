package ircclient.object;

import ircclient.util.Constants;

public class Weather {

    private String city;
    private String state;
    private String country;
    private String sky_description;
    private Double temp;
    private Double temp_high;
    private Double temp_low;
    private Double pressure;

    public Weather() {

    }

    public Weather(String city, String state, String country,
                   String sky_description, Double temp, Double temp_high,
                   Double temp_low, Double pressure) {
        this.city = city;
        this.state = state;
        this.country = country;
        this.sky_description = sky_description;
        this.temp = temp;
        this.temp_high = temp_high;
        this.temp_low = temp_low;
        this.pressure = pressure;
    }

    public String report() {
        String report;
        report = Constants.REPORT_FORECAST_FOR + city;
        if (!city.toLowerCase().contains("county")) {
            report += Constants.REPORT_CITY;
        } else {
            report += ": ";
        }
        report += sky_description.toLowerCase() +
                Constants.REPORT_TEMP + String.format("%.1f", temp) +
                Constants.REPORT_HIGH + String.format("%.1f", temp_high) +
                Constants.REPORT_LOW + String.format("%.1f", temp_low);
        return report;
    }

    public String report(String units) {
        String unitStr = "";
        if (units.equals(Constants.UNITS_FAHRENHEIT)) {
            unitStr = Constants.DEGREES_FAHRENHEIT;
        } else if (units.equals(Constants.UNITS_CELSIUS)) {
            unitStr = Constants.DEGREES_CELSIUS;
        } else if (units.equals(Constants.UNITS_KELVIN)) {
            unitStr = Constants.KELVIN;
        } // else unitStr remains ""
        return sky_description +
                Constants.REPORT_TEMP + String.format("%.1f", temp) + unitStr +
                Constants.REPORT_HIGH + String.format("%.1f", temp_high) + unitStr +
                Constants.REPORT_LOW + String.format("%.1f", temp_low) + unitStr;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSky_description() {
        return sky_description;
    }

    public void setSky_description(String sky_description) {
        if (sky_description.contains("Cloud")) {
            this.sky_description = "Cloudy" + Constants.REPORT_SKY;
        } else if (sky_description.contains("Sun")) {
            this.sky_description = "Sunny" + Constants.REPORT_SKY;
        } else if (sky_description.contains("Mist")) {
            this.sky_description = "Misty";
        } else if (sky_description.contains("Rain")) {
            this.sky_description = "Rainy";
        } else if (sky_description.contains("Clear")) {
            this.sky_description = sky_description + Constants.REPORT_SKY;
        } else {
            this.sky_description = sky_description;
        }
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getTemp_high() {
        return temp_high;
    }

    public void setTemp_high(Double temp_high) {
        this.temp_high = temp_high;
    }

    public Double getTemp_low() {
        return temp_low;
    }

    public void setTemp_low(Double temp_low) {
        this.temp_low = temp_low;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", sky_description='" + sky_description + '\'' +
                ", temp=" + temp +
                ", temp_high=" + temp_high +
                ", temp_low=" + temp_low +
                ", pressure=" + pressure +
                '}';
    }
}
