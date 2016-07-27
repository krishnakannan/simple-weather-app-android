package io.github.krishnakannan.simpleweatherapp.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import io.github.krishnakannan.simpleweatherapp.Model.Area;
import io.github.krishnakannan.simpleweatherapp.Model.CurrentDayWeather;
import io.github.krishnakannan.simpleweatherapp.Model.Weather;
import io.github.krishnakannan.simpleweatherapp.R;
import io.github.krishnakannan.simpleweatherapp.Util.WeatherUtils;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DayFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DayFragment extends Fragment {



    private OnFragmentInteractionListener mListener;

    public DayFragment() {
        // Required empty public constructor
    }

    /*
    * Forecast TextViews
    * */
    private ImageView mainForecastImgView;
    private TextView mainForecastTv;

    private TextView nightEastTv;
    private TextView nightWestTv;
    private TextView nightCentralTv;
    private TextView nightNorthTv;
    private TextView nightSouthTv;

    private TextView mornEastTv;
    private TextView mornWestTv;
    private TextView mornCentralTv;
    private TextView mornSouthTv;
    private TextView mornNorthTv;

    private TextView aftEastTv;
    private TextView aftWestTv;
    private TextView aftNorthTv;
    private TextView aftCentralTv;
    private TextView aftSouthTv;

    private TextView nextNightEastTv;
    private TextView nextNightWestTv;
    private TextView nextNightCentralTv;
    private TextView nextNightNorthTv;
    private TextView nextNightSouthTv;

    /*
    * Forecast ImageViews
    * */

    private ImageView nightEastImgView;
    private ImageView nightWestImgView;
    private ImageView nightCentralImgView;
    private ImageView nightSouthImgView;
    private ImageView nightNorthImgView;

    private ImageView mornEastImgView;
    private ImageView mornWestImgView;
    private ImageView mornNorthImgView;
    private ImageView mornSouthImgView;
    private ImageView mornCentralImgView;

    private ImageView aftEastImgView;
    private ImageView aftWestImgView;
    private ImageView aftNorthImgView;
    private ImageView aftSouthImgView;
    private ImageView aftCentralImgView;

    private ImageView nextNightEastImgView;
    private ImageView nextNightWestImgView;
    private ImageView nextNightNorthImgView;
    private ImageView nextNightSouthImgView;
    private ImageView nextNightCentralImgView;




    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment DayFragment.
     */
    public static DayFragment newInstance() {
        DayFragment fragment = new DayFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_day, container, false);

        mainForecastImgView = (ImageView) rootView.findViewById(R.id.mainImageView);

        nightEastTv = (TextView) rootView.findViewById(R.id.night_east_forecast);
        nightWestTv = (TextView) rootView.findViewById(R.id.night_West_forecast);
        nightCentralTv = (TextView) rootView.findViewById(R.id.night_central_forecast);
        nightNorthTv = (TextView) rootView.findViewById(R.id.night_north_forecast);
        nightSouthTv = (TextView) rootView.findViewById(R.id.night_South_forecast);
        mornEastTv = (TextView) rootView.findViewById(R.id.morning_east_forecast);
        mornWestTv = (TextView) rootView.findViewById(R.id.morning_West_forecast);
        mornNorthTv = (TextView) rootView.findViewById(R.id.morning_north_forecast);
        mornSouthTv = (TextView) rootView.findViewById(R.id.morning_South_forecast);
        mornCentralTv = (TextView) rootView.findViewById(R.id.morning_central_forecast);
        aftEastTv = (TextView) rootView.findViewById(R.id.afternoon_east_forecast);
        aftWestTv = (TextView) rootView.findViewById(R.id.afternoon_West_forecast);
        aftCentralTv = (TextView) rootView.findViewById(R.id.afternoon_central_forecast);
        aftNorthTv = (TextView) rootView.findViewById(R.id.afternoon_north_forecast);
        aftSouthTv = (TextView) rootView.findViewById(R.id.afternoon_South_forecast);
        nextNightEastTv = (TextView) rootView.findViewById(R.id.nextnight_east_forecast);
        nextNightWestTv = (TextView) rootView.findViewById(R.id.nextnight_West_forecast);
        nextNightSouthTv = (TextView) rootView.findViewById(R.id.nextnight_South_forecast);
        nextNightNorthTv = (TextView) rootView.findViewById(R.id.nextnight_north_forecast);
        nextNightCentralTv = (TextView) rootView.findViewById(R.id.nextnight_central_forecast);

        nightEastImgView = (ImageView) rootView.findViewById(R.id.night_east_img);
        nightWestImgView = (ImageView) rootView.findViewById(R.id.night_West_img);
        nightSouthImgView = (ImageView) rootView.findViewById(R.id.night_South_img);
        nightNorthImgView = (ImageView) rootView.findViewById(R.id.night_north_img);
        nightCentralImgView = (ImageView) rootView.findViewById(R.id.night_central_img);
        mornEastImgView = (ImageView) rootView.findViewById(R.id.morning_east_img);
        mornWestImgView = (ImageView) rootView.findViewById(R.id.morning_West_img);
        mornCentralImgView = (ImageView) rootView.findViewById(R.id.morning_central_img);
        mornNorthImgView = (ImageView) rootView.findViewById(R.id.morning_north_img);
        mornSouthImgView = (ImageView) rootView.findViewById(R.id.morning_South_img);
        aftEastImgView = (ImageView) rootView.findViewById(R.id.afternoon_east_img);
        aftWestImgView = (ImageView) rootView.findViewById(R.id.afternoon_West_img);
        aftNorthImgView = (ImageView) rootView.findViewById(R.id.afternoon_north_img);
        aftSouthImgView = (ImageView) rootView.findViewById(R.id.afternoon_South_img);
        aftCentralImgView = (ImageView) rootView.findViewById(R.id.afternoon_central_img);
        nextNightEastImgView = (ImageView) rootView.findViewById(R.id.nextnight_east_img);
        nextNightWestImgView = (ImageView) rootView.findViewById(R.id.nextnight_West_img);
        nextNightCentralImgView = (ImageView) rootView.findViewById(R.id.nextnight_central_img);
        nextNightSouthImgView = (ImageView) rootView.findViewById(R.id.nextnight_South_img);
        nextNightNorthImgView = (ImageView) rootView.findViewById(R.id.nextnight_north_img);

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void updateUI(List<? extends Weather> currentDayWeatherList) {

        for(Object object : currentDayWeatherList) {
            CurrentDayWeather dayWeather = null;
            if (object instanceof CurrentDayWeather) {
                dayWeather = (CurrentDayWeather) object;

                mainForecastImgView.setImageResource(WeatherUtils.getImageResource(dayWeather.getWeather()));

                Area night = dayWeather.getAreaMap().get("night");
                Area morn = dayWeather.getAreaMap().get("morn");
                Area aft = dayWeather.getAreaMap().get("afternoon");
                Area nextNight = dayWeather.getAreaMap().get("nextnight");

                nightEastTv.setText(WeatherUtils.getForecastFromCode(night.getEast()));
                nightWestTv.setText(WeatherUtils.getForecastFromCode(night.getWest()));
                nightCentralTv.setText(WeatherUtils.getForecastFromCode(night.getCentral()));
                nightNorthTv.setText(WeatherUtils.getForecastFromCode(night.getNorth()));
                nightSouthTv.setText(WeatherUtils.getForecastFromCode(night.getSouth()));
                mornEastTv.setText(WeatherUtils.getForecastFromCode(morn.getEast()));
                mornWestTv.setText(WeatherUtils.getForecastFromCode(morn.getWest()));
                mornSouthTv.setText(WeatherUtils.getForecastFromCode(morn.getSouth()));
                mornNorthTv.setText(WeatherUtils.getForecastFromCode(morn.getNorth()));
                mornCentralTv.setText(WeatherUtils.getForecastFromCode(morn.getCentral()));
                aftEastTv.setText(WeatherUtils.getForecastFromCode(aft.getEast()));
                aftWestTv.setText(WeatherUtils.getForecastFromCode(aft.getWest()));
                aftNorthTv.setText(WeatherUtils.getForecastFromCode(aft.getNorth()));
                aftSouthTv.setText(WeatherUtils.getForecastFromCode(aft.getSouth()));
                aftCentralTv.setText(WeatherUtils.getForecastFromCode(aft.getCentral()));
                nextNightEastTv.setText(WeatherUtils.getForecastFromCode(nextNight.getEast()));
                nextNightWestTv.setText(WeatherUtils.getForecastFromCode(nextNight.getWest()));
                nextNightNorthTv.setText(WeatherUtils.getForecastFromCode(nextNight.getNorth()));
                nextNightSouthTv.setText(WeatherUtils.getForecastFromCode(nextNight.getSouth()));
                nextNightCentralTv.setText(WeatherUtils.getForecastFromCode(nextNight.getCentral()));


                nightEastImgView.setImageResource(WeatherUtils.getImageResource(night.getEast()));
                nightWestImgView.setImageResource(WeatherUtils.getImageResource(night.getWest()));
                nightCentralImgView.setImageResource(WeatherUtils.getImageResource(night.getCentral()));
                nightNorthImgView.setImageResource(WeatherUtils.getImageResource(night.getNorth()));
                nightSouthImgView.setImageResource(WeatherUtils.getImageResource(night.getSouth()));
                mornEastImgView.setImageResource(WeatherUtils.getImageResource(morn.getEast()));
                mornWestImgView.setImageResource(WeatherUtils.getImageResource(morn.getWest()));
                mornSouthImgView.setImageResource(WeatherUtils.getImageResource(morn.getSouth()));
                mornNorthImgView.setImageResource(WeatherUtils.getImageResource(morn.getNorth()));
                mornCentralImgView.setImageResource(WeatherUtils.getImageResource(morn.getCentral()));
                aftEastImgView.setImageResource(WeatherUtils.getImageResource(aft.getEast()));
                aftWestImgView.setImageResource(WeatherUtils.getImageResource(aft.getWest()));
                aftNorthImgView.setImageResource(WeatherUtils.getImageResource(aft.getNorth()));
                aftSouthImgView.setImageResource(WeatherUtils.getImageResource(aft.getSouth()));
                aftCentralImgView.setImageResource(WeatherUtils.getImageResource(aft.getCentral()));
                nextNightEastImgView.setImageResource(WeatherUtils.getImageResource(nextNight.getEast()));
                nextNightWestImgView.setImageResource(WeatherUtils.getImageResource(nextNight.getWest()));
                nextNightNorthImgView.setImageResource(WeatherUtils.getImageResource(nextNight.getNorth()));
                nextNightSouthImgView.setImageResource(WeatherUtils.getImageResource(nextNight.getSouth()));
                nextNightCentralImgView.setImageResource(WeatherUtils.getImageResource(nextNight.getCentral()));
            }

        }

    }

}
