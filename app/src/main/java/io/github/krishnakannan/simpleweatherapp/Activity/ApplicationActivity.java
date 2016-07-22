package io.github.krishnakannan.simpleweatherapp.Activity;

import android.Manifest;
import android.app.Fragment;
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
import io.github.krishnakannan.simpleweatherapp.Util.WeatherIcons;

public class ApplicationActivity extends AppCompatActivity implements
        HomeFragment.OnFragmentInteractionListener,
        DayFragment.OnFragmentInteractionListener,
        WeekFragment.OnFragmentInteractionListener {

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


        Fragment dayFragment;
        Fragment weekFragment;





        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(location != null && location.getTime() > Calendar.getInstance().getTimeInMillis() - 2 * 60 * 1000) {
            setLocation(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));
        }
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                setLocation(location);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //For android 6 and above
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_ALL_PERMISSIONS);
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);


        //Home Details

        NetworkHelper.NeighborhoodCallback<String> neighborhoodCallback = new NetworkHelper.NeighborhoodCallback<String>() {
            public void onSuccess(String response) {
                neighborhood = response;

                NetworkHelper.Callback<byte[]> currentForecast = new NetworkHelper.Callback<byte[]>() {
                    public void onSuccess(List<? extends Weather> response) {

                        for(Object object : response) {
                            CurrentWeather currentWeather = null;
                            if (object instanceof CurrentWeather) {
                                currentWeather = (CurrentWeather) object;

                                if (currentWeather.getArea().equalsIgnoreCase(neighborhood)) {
                                    homeFragment = (HomeFragment) mSectionsPagerAdapter.getFragment(AppConstants.HOME);
                                    homeFragment.updateUI(WeatherIcons.getImageResource(currentWeather.getForecast()), currentWeather.area, currentWeather.forecast);
                                }
                            }

                        }
                    }
                };

                NetworkHelper.getCurrentForecast(getApplicationContext(), currentForecast);

            }
        };

        NetworkHelper.getNeighborhood(neighborhoodCallback, getApplicationContext(), getLocation());
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
}
