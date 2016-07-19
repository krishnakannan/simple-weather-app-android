package io.github.krishnakannan.simpleweatherapp.Model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Krish on 20/07/16.
 */
public class CurrentDayWeather extends Weather {

    String weather;

    String forecast;

    Map<String, Area> areaMap;

    public CurrentDayWeather(Map<String, Area> areaMap,  String weather, String forecast) {
        this.areaMap = new HashMap<>();
        this.areaMap.putAll(areaMap);
        this.weather = weather;
        this.forecast = forecast;
    }

}
