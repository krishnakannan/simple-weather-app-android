package io.github.krishnakannan.simpleweatherapp.NetworkUtils;

import android.content.Context;
import android.location.Location;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import io.github.krishnakannan.simpleweatherapp.Model.CurrentDayWeather;
import io.github.krishnakannan.simpleweatherapp.Model.CurrentWeather;
import io.github.krishnakannan.simpleweatherapp.Model.CurrentWeekWeather;
import io.github.krishnakannan.simpleweatherapp.Model.Weather;
import io.github.krishnakannan.simpleweatherapp.Parser.CurrentDayWeatherParser;
import io.github.krishnakannan.simpleweatherapp.Parser.CurrentWeatherParser;
import io.github.krishnakannan.simpleweatherapp.Parser.CurrentWeekWeatherParser;
import io.github.krishnakannan.simpleweatherapp.Util.AppConstants;
import io.github.krishnakannan.simpleweatherapp.Util.SimpleWeatherApplication;

/**
 * Created by Krish on 19/07/16.
 */
public class NetworkHelper {

    public static class Callback<T> {
        public void onSuccess(List<? extends Weather> result) { /* Do nothing. */ }
        public void onError(VolleyError error) { /* Do nothing. */ }
    }

    public static class NeighborhoodCallback<T> {
        public void onSuccess(String result) { /* Do nothing. */ }
        public void onError(String error) { /* Do nothing. */ }
    }

    static List<CurrentWeather> currentWeatherList = new ArrayList<>();
    static List<CurrentDayWeather> currentDayWeatherList = new ArrayList<>();
    static List<CurrentWeekWeather> currentWeekWeatherList = new ArrayList<>();

    public static void getNeighborhood(final NeighborhoodCallback<String> callback, Context context, Location location) {
        Double latitude = 1.38000000;
        Double longitude = 103.80500000;
        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }
        RequestQueue queue = SimpleWeatherApplication.getInstance(context).getRequestQueue();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, AppConstants.MAPS_API + latitude
                + "," + longitude + "&key=" + AppConstants.MAPS_API_KEY, null , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray results = response.getJSONArray("results");
                    JSONObject object = results.getJSONObject(0);
                    JSONArray addrComponent = object.getJSONArray("address_components");
                    List<JSONObject> addrComponents = new ArrayList<>();
                    for(int i = 0; i < addrComponent.length(); i++) {
                        addrComponents.add(addrComponent.getJSONObject(i));
                    }

                    for(JSONObject individualComponent : addrComponents) {
                        JSONArray types = individualComponent.getJSONArray("types");
                        String strtype1 = "";
                        String strtype2 = "";
                        if(types.length() > 1){
                            strtype1 = types.get(0).toString();
                            strtype2 = types.get(1).toString();
                        } else {
                            strtype1 = types.get(0).toString();
                        }

                        if (strtype1.equals("neighborhood") || strtype2.equals("neighborhood")) {
                            callback.onSuccess(individualComponent.getString("long_name"));
                        }
                    }
                } catch (JSONException je) {
                    callback.onError(je.getLocalizedMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);

    }

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
                InputStream is = new ByteArrayInputStream(response);
                CurrentDayWeatherParser currentDayWeatherParser = new CurrentDayWeatherParser();
                try {
                    currentDayWeatherList = currentDayWeatherParser.parse(is);
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

                callback.onSuccess(currentDayWeatherList);
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
                InputStream is = new ByteArrayInputStream(response);
                CurrentWeekWeatherParser currentWeekWeatherParser = new CurrentWeekWeatherParser();
                try {
                    currentWeekWeatherList = currentWeekWeatherParser.parse(is);
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

                callback.onSuccess(currentWeekWeatherList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(weekForecastRequest);
    }
}
