package io.github.krishnakannan.simpleweatherapp.Model;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Krish on 20/07/16.
 */
public class CurrentDayWeather extends Weather {

    String weather;

    String forecast;

    Map<String, Area> areaMap;

    public CurrentDayWeather(Map<String, Area> areaMap,  String weather, String forecast) {
        this.areaMap = new LinkedHashMap<>();
        this.areaMap.putAll(areaMap);
        this.weather = weather;
        this.forecast = forecast;
    }

    public String getWeather() {
        return this.weather;
    }

    public String getForecast() {
        return this.forecast;
    }

    public Map<String, Area> getAreaMap() {
        return areaMap;
    }
}
