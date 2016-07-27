package io.github.krishnakannan.simpleweatherapp.Activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Calendar;
import java.util.List;

import io.github.krishnakannan.simpleweatherapp.Adapter.SectionsPagerAdapter;
import io.github.krishnakannan.simpleweatherapp.Fragment.DayFragment;
import io.github.krishnakannan.simpleweatherapp.Fragment.HomeFragment;
import io.github.krishnakannan.simpleweatherapp.Fragment.WeekFragment;
import io.github.krishnakannan.simpleweatherapp.Model.CurrentWeather;
import io.github.krishnakannan.simpleweatherapp.Model.Weather;
import io.github.krishnakannan.simpleweatherapp.NetworkUtils.NetworkHelper;
import io.github.krishnakannan.simpleweatherapp.R;
import io.github.krishnakannan.simpleweatherapp.Util.AppConstants;
import io.github.krishnakannan.simpleweatherapp.Util.WeatherUtils;

public class ApplicationActivity extends AppCompatActivity implements
        HomeFragment.OnFragmentInteractionListener,
        DayFragment.OnFragmentInteractionListener,
        WeekFragment.OnFragmentInteractionListener, LocationListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private static final int REQUEST_ALL_PERMISSIONS = 1;

    public Location location = null;

    public String neighborhood = "";

    HomeFragment homeFragment;

    DayFragment dayFragment;

    WeekFragment weekFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(2);

        // Acquire a reference to the system Location Manager

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //For android 6 and above
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_ALL_PERMISSIONS);
            return;
        }
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(location != null && location.getTime() > Calendar.getInstance().getTimeInMillis() - 2 * 60 * 1000) {
            setLocation(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));
        }

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3000, 0, this);

        final NetworkHelper.Callback<byte[]> dayForecast = new NetworkHelper.Callback<byte[]>() {
            public void onSuccess(List<? extends Weather> response) {
                dayFragment = (DayFragment) mSectionsPagerAdapter.getFragment(AppConstants.DAY);
                dayFragment.updateUI(response);
            }
        };

        NetworkHelper.getDayForecast(getApplicationContext(), dayForecast);


        NetworkHelper.Callback<byte[]> weekForecast = new NetworkHelper.Callback<byte[]>() {
            public void onSuccess(List<? extends Weather> response) {
                weekFragment = (WeekFragment) mSectionsPagerAdapter.getFragment(AppConstants.WEEK);
                weekFragment.updateUI(response);
            }
        };

        NetworkHelper.getWeekForecast(getApplicationContext(), weekForecast);

    }


    public void setLocation(Location location) {
        Log.i("LOCATION" , Double.toString(location.getLatitude()) + " " + Double.toString(location.getLongitude()));
        this.location = location;
    }

    public Location getLocation() {
        return this.location;
    }

    public String getNeighborhood() {
        return this.neighborhood;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onLocationChanged(Location location) {
        setLocation(location);
        //Home Details

        NetworkHelper.NeighborhoodCallback<String> neighborhoodCallback = new NetworkHelper.NeighborhoodCallback<String>() {
            public void onSuccess(String response) {
                neighborhood = response;
                Log.i("getNeighborhood", response);
                NetworkHelper.Callback<byte[]> currentForecast = new NetworkHelper.Callback<byte[]>() {
                    public void onSuccess(List<? extends Weather> response) {
                        boolean locationInsideSingapore = false;
                        CurrentWeather cityWeather = null;
                        for(Object object : response) {
                            CurrentWeather currentWeather = null;

                            if (object instanceof CurrentWeather) {
                                currentWeather = (CurrentWeather) object;

                                if (currentWeather.getArea().equalsIgnoreCase(neighborhood)) {
                                    homeFragment = (HomeFragment) mSectionsPagerAdapter.getFragment(AppConstants.HOME);
                                    homeFragment.updateUI(WeatherUtils.getImageResource(currentWeather.getForecast()), currentWeather.area, currentWeather.forecast);
                                    locationInsideSingapore = true;
                                }
                                if (currentWeather.getArea().equalsIgnoreCase("City")) {
                                    cityWeather = (CurrentWeather) object;
                                }
                            }

                        }
                        if (!locationInsideSingapore) {
                            //Outside Singapore Application shows weather of "City" neighborhood
                            homeFragment = (HomeFragment) mSectionsPagerAdapter.getFragment(AppConstants.HOME);
                            homeFragment.updateUI(WeatherUtils.getImageResource(cityWeather.getForecast()), cityWeather.area, cityWeather.forecast);
                        }

                    }
                };

                NetworkHelper.getCurrentForecast(getApplicationContext(), currentForecast);

            }

            public void onError(String error) {
                Log.e("getNeighborhood", error);
            }
        };

        NetworkHelper.getNeighborhood(neighborhoodCallback, getApplicationContext(), getLocation());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
