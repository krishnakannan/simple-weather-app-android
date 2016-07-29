package io.github.krishnakannan.simpleweatherapp.Model;

/**
 * Created by Krish on 20/07/16.
 */
public class CurrentWeekWeather extends Weather {

    String day;

    String forecast;

    String icon;

    String temperature;

    public CurrentWeekWeather(String day, String forecast, String icon, String temperature) {
        this.day = day;
        this.forecast = forecast;
        this.icon = icon;
        this.temperature = temperature;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getDay() {
        return day;
    }

    public String getForecast() {
        return forecast;
    }

    public String getIcon() {
        return icon;
    }

}
