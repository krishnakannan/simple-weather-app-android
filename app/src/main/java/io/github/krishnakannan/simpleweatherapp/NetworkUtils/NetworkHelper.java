package io.github.krishnakannan.simpleweatherapp.NetworkUtils;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import io.github.krishnakannan.simpleweatherapp.Model.CurrentWeather;
import io.github.krishnakannan.simpleweatherapp.Parser.CurrentWeatherParser;
import io.github.krishnakannan.simpleweatherapp.Util.AppConstants;
import io.github.krishnakannan.simpleweatherapp.Util.SimpleWeatherApplication;

/**
 * Created by Krish on 19/07/16.
 */
public class NetworkHelper {

    public static class Callback<T> {
        public void onSuccess(List<?> result) { /* Do nothing. */ }
        public void onError(VolleyError error) { /* Do nothing. */ }
    }

    static List<CurrentWeather> currentWeatherList = new ArrayList<CurrentWeather>();

    public static void getCurrentForecast(Context context, final Callback<byte[]> callback) {
        RequestQueue queue = SimpleWeatherApplication.getInstance(context).getRequestQueue();
        InputStreamRequest currentForecastRequest = new InputStreamRequest(Request.Method.GET, AppConstants.CURRENT_FORECAST_API_URL, new Response.Listener<byte[]>() {
            @Override
            public void onResponse(byte[] response) {
                InputStream is = new ByteArrayInputStream(response);
                CurrentWeatherParser currentWeatherParser = new CurrentWeatherParser();
                try {
                    currentWeatherList = currentWeatherParser.parse(is);
                } catch (XmlPullParserException xpe) {

                } catch (IOException ioe) {

                } finally {
                  try{
                      if (is != null) {
                          is.close();
                      }
                  } catch (IOException ioe) {

                  }
                }



                callback.onSuccess(currentWeatherList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(currentForecastRequest);
    }

    public static void getDayForecast(Context context, final Callback<byte[]> callback) {
        RequestQueue queue = SimpleWeatherApplication.getInstance(context).getRequestQueue();
        InputStreamRequest dayForecastRequest = new InputStreamRequest(Request.Method.GET, AppConstants.DAY_FORECAST_API_URL, new Response.Listener<byte[]>() {
            @Override
            public void onResponse(byte[] response) {
                callback.onSuccess(currentWeatherList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(dayForecastRequest);
    }

    public static void getWeekForecast(Context context, final Callback<byte[]> callback) {
        RequestQueue queue = SimpleWeatherApplication.getInstance(context).getRequestQueue();
        InputStreamRequest weekForecastRequest = new InputStreamRequest(Request.Method.GET, AppConstants.WEEK_FORECAST_API_URL, new Response.Listener<byte[]>() {
            @Override
            public void onResponse(byte[] response) {
                callback.onSuccess(currentWeatherList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(weekForecastRequest);
    }
}
