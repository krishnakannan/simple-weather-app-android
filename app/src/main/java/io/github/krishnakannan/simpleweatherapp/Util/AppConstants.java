package io.github.krishnakannan.simpleweatherapp.Util;

/**
 * Created by Krish on 18/07/16.
 */
public class AppConstants {

    public static String CURRENT_FORECAST_API_URL = "http://www.nea.gov.sg/api/WebAPI/?dataset=2hr_nowcast&keyref=781CF461BB6606AD120881175FC740619D8FB25A3EA2FF69";

    public static String DAY_FORECAST_API_URL = "http://www.nea.gov.sg/api/WebAPI/?dataset=24hrs_forecast&keyref=781CF461BB6606AD120881175FC740619D8FB25A3EA2FF69";

    public static String WEEK_FORECAST_API_URL = "http://www.nea.gov.sg/api/WebAPI/?dataset=4days_outlook&keyref=781CF461BB6606AD120881175FC740619D8FB25A3EA2FF69";

    public static String MAPS_API = "https://maps.googleapis.com/maps/api/geocode/json?latlng=";

    public static  String MAPS_API_KEY = "AIzaSyBEnVbNq5W2XBO8yIVsrgSxAVjh4_zLYaw";

    public static String HOME = "HomeFragment";

    public static String DAY = "DayFragment";

    public static String WEEK = "WeekFragment";
}
