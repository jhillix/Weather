package com.jhillix.yahoo.weather;


/**
 * The Weather object as described in "Maven: The Definitive Guide".
 *
 * @author jhillix
 */
public class Weather {

    private String city;

    private String region;

    private String country;

    private String condition;

    private String temp;

    private String chill;

    private String humidity;

    public Weather() {}

    public String getCity() {
        return city;
    }

    public String getRegion() {
        return region;
    }

    public String getCountry() {
        return country;
    }

    public String getCondition() {
        return condition;
    }

    public String getTemp() {
        return temp;
    }

    public String getChill() {
        return chill;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public void setRegion(final String region) {
        this.region = region;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public void setCondition(final String condition) {
        this.condition = condition;
    }

    public void setTemp(final String temp) {
        this.temp = temp;
    }

    public void setChill(final String chill) {
        this.chill = chill;
    }

    public void setHumidity(final String humidity) {
        this.humidity = humidity;
    }
}
