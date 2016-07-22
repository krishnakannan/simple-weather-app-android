package io.github.krishnakannan.simpleweatherapp.Parser;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import io.github.krishnakannan.simpleweatherapp.Model.CurrentWeekWeather;

/**
 * Created by Krish on 20/07/16.
 */
public class CurrentWeekWeatherParser {

    private static final String ns = null;

    public List<CurrentWeekWeather> parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            List<CurrentWeekWeather> currentWeekWeatherList = new ArrayList<>();
            currentWeekWeatherList.addAll(readChannel(parser));
            return currentWeekWeatherList;
        } finally {
            in.close();
        }
    }

    public List<CurrentWeekWeather> readChannel(XmlPullParser parser) throws XmlPullParserException,IOException {
        List<CurrentWeekWeather> currentWeekWeatherList = new ArrayList<>();

        parser.require(XmlPullParser.START_TAG, ns, "channel");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            String name = parser.getName();
            // Starts by looking for the entry tag
            if (name.equals("item")) {
                currentWeekWeatherList.addAll(readItem(parser));
                return currentWeekWeatherList;
            } else {
                skip(parser);
            }

        }
        return null;
    }

    private List<CurrentWeekWeather> readItem(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<CurrentWeekWeather> entries = new ArrayList<>();
        parser.require(XmlPullParser.START_TAG, ns, "item");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("weatherForecast")) {
                entries.addAll(readWeather(parser));
            } else {
                skip(parser);
            }
        }
        return entries;

    }

    private List<CurrentWeekWeather> readWeather(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<CurrentWeekWeather> entries = new ArrayList<>();
        Integer daysCount = 1;
        parser.require(XmlPullParser.START_TAG, ns, "weatherForecast");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            while (daysCount <= 4) {
                daysCount++;
                String name = parser.getName();
                CurrentWeekWeather currentWeekWeather = null;
                String day = "";
                String icon = "";
                String temperature = "";
                String forecast = "";
                if (parser.getName().equals("day")) {
                    day = readTag(parser, "day");
                    parser.next();
                    while(!parser.getName().equals("wind")) {
                        if (parser.getName().equals("forecast")) {
                            forecast = readTag(parser, "forecast");
                        } else if (parser.getName().equals("icon")) {
                            icon = readTag(parser, "icon");
                        } else if (parser.getName().equals("temperature")) {
                            temperature = parser.getAttributeValue(ns,"high") + " C - " +  parser.getAttributeValue(ns,"low") + " C";
                        }
                        parser.next();
                    }
                    currentWeekWeather = new CurrentWeekWeather(day, forecast, icon, temperature);
                    entries.add(currentWeekWeather);
                    parser.next();
                    parser.next();
                } else {
                    skip(parser);
                }
            }
        }
        return entries;
    }



    private String readTag(XmlPullParser parser, String tag) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, tag);
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, tag);
        return title;
    }

    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }

}
