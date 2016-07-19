package io.github.krishnakannan.simpleweatherapp.Parser;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.krishnakannan.simpleweatherapp.Model.Area;
import io.github.krishnakannan.simpleweatherapp.Model.CurrentDayWeather;

/**
 * Created by Krish on 20/07/16.
 */
public class CurrentDayWeatherParser {

    private static final String ns = null;

    public List<CurrentDayWeather> parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            List<CurrentDayWeather> currentDayWeather = new ArrayList<>();
            currentDayWeather.add(readChannel(parser));
            return currentDayWeather;
        } finally {
            in.close();
        }
    }

    public CurrentDayWeather readChannel(XmlPullParser parser) throws XmlPullParserException,IOException {
        CurrentDayWeather currentDayWeather = null;
        Map<String, String> mainSummary = new HashMap<>();
        Map<String, Area> areaMap = new HashMap<>();
        parser.require(XmlPullParser.START_TAG, ns, "channel");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry tag
            if (name.equals("main")) {
                mainSummary.putAll(readMain(parser));
            } else if (name.equals("night")) {
                areaMap.put("night", readArea(parser,"night"));
            } else if (name.equals("morn")) {
                areaMap.put("morn", readArea(parser,"morn"));
            } else if (name.equals("afternoon")) {
                areaMap.put("afternoon", readArea(parser,"afternoon"));
            } else if (name.equals("nextnight")) {
                areaMap.put("nextnight", readArea(parser,"nextnight"));
            } else {
                skip(parser);
            }
        }

        currentDayWeather = new CurrentDayWeather(areaMap, mainSummary.get("wxmain"), mainSummary.get("forecast"));

        return currentDayWeather;
    }

    private Area readArea(XmlPullParser parser, String tag) throws XmlPullParserException, IOException {
        String east = "";
        String west = "";
        String south = "";
        String north = "";
        String central = "";
        parser.require(XmlPullParser.START_TAG, ns, tag);
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("wxeast")) {
                east = readTag(parser, "wxeast");
            } else if (name.equals("wxwest")) {
                west = readTag(parser, "wxwest");
            }  else if (name.equals("wxnorth")) {
                north = readTag(parser, "wxnorth");
            }  else if (name.equals("wxsouth")) {
                south = readTag(parser, "wxsouth");
            }  else if (name.equals("wxcentral")) {
                central = readTag(parser, "wxcentral");
            } else {
                skip(parser);
            }
        }
        Area area = new Area(east, west, north, south, central);
        return area;

    }

    private Map<String, String> readMain(XmlPullParser parser) throws XmlPullParserException, IOException {
        Map<String, String> mainSummary = new HashMap<>();
        parser.require(XmlPullParser.START_TAG, ns, "main");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("wxmain")) {
                mainSummary.put("wxmain", readTag(parser, "wxmain"));
            } else if (name.equals("forecast")) {
                mainSummary.put("forecast", readTag(parser, "forecast"));
            } else {
                skip(parser);
            }
        }
        return mainSummary;

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
