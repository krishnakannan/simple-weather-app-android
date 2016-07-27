package io.github.krishnakannan.simpleweatherapp.Util;

import io.github.krishnakannan.simpleweatherapp.R;

/**
 * Created by Krish on 21/07/16.
 */
public class WeatherUtils {

    public static int getImageResource(String weatherCode) {
        if (weatherCode.equals("CL")) {
            return R.drawable.cloud;
        } else if (weatherCode.equals("PC") || weatherCode.equals("PN")) {
            return R.drawable.cloudy;
        } else if (weatherCode.equals("FG") || weatherCode.equals("BR") || weatherCode.equals("LH")) {
            return R.drawable.fog;
        } else if (weatherCode.equals("HZ") || weatherCode.equals("WC") || weatherCode.equals("WF")) {
            return R.drawable.fog_cloudy;
        } else if (weatherCode.equals("HT") || weatherCode.equals("SR") || weatherCode.equals("WS")) {
            return R.drawable.hail;
        } else if (weatherCode.equals("FN")) {
            return R.drawable.moon;
        } else if (weatherCode.equals("DR") || weatherCode.equals("LR") || weatherCode.equals("LS") || weatherCode.equals("RA") || weatherCode.equals("PS") || weatherCode.equals("SH")) {
            return R.drawable.rain;
        } else if (weatherCode.equals("SN") || weatherCode.equals("SS")) {
            return R.drawable.snow;
        } else if (weatherCode.equals("FA") || weatherCode.equals("FW") || weatherCode.equals("SU")) {
            return R.drawable.sun;
        } else if (weatherCode.equals("HR") || weatherCode.equals("HS") || weatherCode.equals("TL") || weatherCode.equals("WR")) {
            return R.drawable.thunderstorm;
        } else if (weatherCode.equals("HG") || weatherCode.equals("SK")) {
            return R.drawable.tornado;
        } else if (weatherCode.equals("OC")) {
            return R.drawable.very_cloudy;
        }  else if (weatherCode.equals("SW") || weatherCode.equals("WD")) {
            return R.drawable.windy;
        } else {
            return R.drawable.cloud;
        }
    }

    public static String getForecastFromCode(String weatherCode) {
        switch (weatherCode) {
            case "CL":
                return "Cloudy";
            case "PC":
                return "Partly Cloudy (Day)";
            case "PN":
                return "Partly Cloudy (Night)";
            case "FG":
                return "Fog";
            case "BR":
                return "Mist";
            case "LH":
                return "Slightly Hazy";
            case "HZ":
                return "Hazy";
            case "WC":
                return "Windy, Cloudy";
            case "WF":
                return "Windy, Fair";
            case "HT":
                return "Heavy Thundery Showers ";
            case "SR":
                return "Strong Winds, Rain";
            case "WS":
                return "Windy, Showers";
            case "FN":
                return "Fair (Night)";
            case "DR":
                return "Drizzle";
            case "LR":
                return "Light Rain";
            case "LS":
                return "Light Showers";
            case "RA":
                return "Moderate Rain";
            case "PS":
                return "Passing Showers";
            case "SH":
                return "Showers";
            case "SN":
                return "Snow";
            case "SS":
                return "Snow Showers";
            case "FA":
                return "Fair (Day)";
            case "FW":
                return "Fair & Warm";
            case "SU":
                return "Sunny";
            case "HR":
                return "Heavy Rain";
            case "HS":
                return "Heavy Showers";
            case "TL":
                return "Thundery Showers";
            case "WR":
                return "Windy, Rain";
            case "HG":
                return "Heavy Thundery Showers with Gusty Winds";
            case "SK":
                return "Strong Winds, Showers";
            case "OC":
                return "Overcast";
            case "SW":
                return "Strong Winds";
            case "WD":
                return "Windy";
            default:
                return "err";
        }
    }

}
