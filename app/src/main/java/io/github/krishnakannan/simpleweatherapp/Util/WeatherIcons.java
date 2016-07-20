package io.github.krishnakannan.simpleweatherapp.Util;

import io.github.krishnakannan.simpleweatherapp.R;

/**
 * Created by Krish on 21/07/16.
 */
public class WeatherIcons {

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

}
