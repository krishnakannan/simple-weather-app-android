package io.github.krishnakannan.simpleweatherapp.Model;

/**
 * Created by Krish on 19/07/16.
 */
public class CurrentWeather {

    public String forecast;

    public String area;

    public String getForecast() {
        return forecast;
    }

    public String getArea() {
        return area;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String latitude;

    public String longitude;

    public CurrentWeather(String forecast, String area, String latitude, String longitude) {
        this.area = area;
        this.forecast = forecast;
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
