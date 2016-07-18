package io.github.krishnakannan.simpleweatherapp.Util;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Krish on 18/07/16.
 */
public class SimpleWeatherApplication {

    private static SimpleWeatherApplication instance;
    private RequestQueue requestQueue;
    public static Context context;

    private SimpleWeatherApplication() { }

    private SimpleWeatherApplication(Context context) {
        this.context = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized SimpleWeatherApplication getInstance(Context context) {
        if (instance == null) {
            instance = new SimpleWeatherApplication(context);
        }
        return instance;
    }


    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return this.requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }
}
