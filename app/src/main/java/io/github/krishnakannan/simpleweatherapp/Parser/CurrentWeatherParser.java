package io.github.krishnakannan.simpleweatherapp.Parser;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import io.github.krishnakannan.simpleweatherapp.Model.CurrentWeather;

/**
 * Created by Krish on 19/07/16.
 */
public class CurrentWeatherParser {

    private static final String ns = null;

    /**
     *
     * Scenario : Parsing current weather results from NEA - National Environmental Agency.
     * The response contains weather information about the current weather for all neighborhood in Singapore.
     *
     * @param in
     * @return
     * @throws XmlPullParserException
     * @throws IOException
     */
    public List<CurrentWeather> parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readChannel(parser);
        } finally {
            in.close();
        }
    }

    private List<CurrentWeather> readChannel(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<CurrentWeather> entries = new ArrayList<>();

        parser.require(XmlPullParser.START_TAG, ns, "channel");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry tag
            if (name.equals("item")) {
                entries.addAll(readItem(parser));
            } else {
                skip(parser);
            }
        }
        return entries;
    }

    private List<CurrentWeather> readItem(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<CurrentWeather> entries = new ArrayList<>();
        parser.require(XmlPullParser.START_TAG, ns, "item");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("weatherForecast")) {
                entries.addAll(readArea(parser));
            } else {
                skip(parser);
            }
        }
        return entries;

    }

    private List<CurrentWeather> readArea(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "weatherForecast");
        List<CurrentWeather> entries = new ArrayList<>();
        String forecast = null;
        String latitude = null;
        String longitude = null;
        String area = null;
        parser.next();
        while (parser.getName().equals("area")) {

            String name = parser.getName();
            if (name.equals("area")) {
                forecast = parser.getAttributeValue(ns,"forecast");
                area = parser.getAttributeValue(ns,"name");
                latitude = parser.getAttributeValue(ns,"lat");
                longitude = parser.getAttributeValue(ns,"lon");
                entries.add(new CurrentWeather(forecast, area, latitude, longitude));
            } else {
                skip(parser);
            }
            parser.next();
        }
        return entries;
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
