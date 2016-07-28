package io.github.krishnakannan.simpleweatherapp.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

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

    private LinearLayout dayFirstLayout;
    private LinearLayout daySecondLayout;
    private LinearLayout dayThirdLayout;
    private LinearLayout dayFourthLayout;

    private TextView firstTv;
    private TextView secondTv;
    private TextView thirdTv;
    private TextView fourthTv;

    private ImageView mainForecastImgView;
    private TextView mainForecastTv;

    private TextView firstEastTv;
    private TextView firstWestTv;
    private TextView firstCentralTv;
    private TextView firstNorthTv;
    private TextView firstSouthTv;

    private TextView secondEastTv;
    private TextView secondWestTv;
    private TextView secondCentralTv;
    private TextView secondSouthTv;
    private TextView secondNorthTv;

    private TextView thirdEastTv;
    private TextView thirdWestTv;
    private TextView thirdNorthTv;
    private TextView thirdCentralTv;
    private TextView thirdSouthTv;

    private TextView fourthEastTv;
    private TextView fourthWestTv;
    private TextView fourthCentralTv;
    private TextView fourthNorthTv;
    private TextView fourthSouthTv;

    /*
    * Forecast ImageViews
    * */

    private ImageView firstEastImgView;
    private ImageView firstWestImgView;
    private ImageView firstCentralImgView;
    private ImageView firstSouthImgView;
    private ImageView firstNorthImgView;

    private ImageView secondEastImgView;
    private ImageView secondWestImgView;
    private ImageView secondNorthImgView;
    private ImageView secondSouthImgView;
    private ImageView secondCentralImgView;

    private ImageView thirdEastImgView;
    private ImageView thirdWestImgView;
    private ImageView thirdNorthImgView;
    private ImageView thirdSouthImgView;
    private ImageView thirdCentralImgView;

    private ImageView fourthEastImgView;
    private ImageView fourthWestImgView;
    private ImageView fourthNorthImgView;
    private ImageView fourthSouthImgView;
    private ImageView fourthCentralImgView;




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
        mainForecastTv = (TextView) rootView.findViewById(R.id.day_forecast);
        dayFirstLayout = (LinearLayout) rootView.findViewById(R.id.dayfirstLayout);
        daySecondLayout = (LinearLayout) rootView.findViewById(R.id.daysecondLayout);
        dayThirdLayout = (LinearLayout) rootView.findViewById(R.id.daythirdLayout);
        dayFourthLayout = (LinearLayout) rootView.findViewById(R.id.dayfourthLayout);
        firstTv = (TextView) rootView.findViewById(R.id.first_textview);
        secondTv = (TextView) rootView.findViewById(R.id.second_textview);
        thirdTv = (TextView) rootView.findViewById(R.id.third_textview);
        fourthTv = (TextView) rootView.findViewById(R.id.fourth_textview);


        firstEastTv = (TextView) rootView.findViewById(R.id.first_east_forecast);
        firstWestTv = (TextView) rootView.findViewById(R.id.first_West_forecast);
        firstCentralTv = (TextView) rootView.findViewById(R.id.first_central_forecast);
        firstNorthTv = (TextView) rootView.findViewById(R.id.first_north_forecast);
        firstSouthTv = (TextView) rootView.findViewById(R.id.first_South_forecast);
        secondEastTv = (TextView) rootView.findViewById(R.id.second_east_forecast);
        secondWestTv = (TextView) rootView.findViewById(R.id.second_West_forecast);
        secondNorthTv = (TextView) rootView.findViewById(R.id.second_north_forecast);
        secondSouthTv = (TextView) rootView.findViewById(R.id.second_South_forecast);
        secondCentralTv = (TextView) rootView.findViewById(R.id.second_central_forecast);
        thirdEastTv = (TextView) rootView.findViewById(R.id.third_east_forecast);
        thirdWestTv = (TextView) rootView.findViewById(R.id.third_West_forecast);
        thirdCentralTv = (TextView) rootView.findViewById(R.id.third_central_forecast);
        thirdNorthTv = (TextView) rootView.findViewById(R.id.third_north_forecast);
        thirdSouthTv = (TextView) rootView.findViewById(R.id.third_South_forecast);
        fourthEastTv = (TextView) rootView.findViewById(R.id.fourth_east_forecast);
        fourthWestTv = (TextView) rootView.findViewById(R.id.fourth_West_forecast);
        fourthSouthTv = (TextView) rootView.findViewById(R.id.fourth_South_forecast);
        fourthNorthTv = (TextView) rootView.findViewById(R.id.fourth_north_forecast);
        fourthCentralTv = (TextView) rootView.findViewById(R.id.fourth_central_forecast);

        firstEastImgView = (ImageView) rootView.findViewById(R.id.first_east_img);
        firstWestImgView = (ImageView) rootView.findViewById(R.id.first_West_img);
        firstSouthImgView = (ImageView) rootView.findViewById(R.id.first_South_img);
        firstNorthImgView = (ImageView) rootView.findViewById(R.id.first_north_img);
        firstCentralImgView = (ImageView) rootView.findViewById(R.id.first_central_img);
        secondEastImgView = (ImageView) rootView.findViewById(R.id.second_east_img);
        secondWestImgView = (ImageView) rootView.findViewById(R.id.second_West_img);
        secondCentralImgView = (ImageView) rootView.findViewById(R.id.second_central_img);
        secondNorthImgView = (ImageView) rootView.findViewById(R.id.second_north_img);
        secondSouthImgView = (ImageView) rootView.findViewById(R.id.second_South_img);
        thirdEastImgView = (ImageView) rootView.findViewById(R.id.third_east_img);
        thirdWestImgView = (ImageView) rootView.findViewById(R.id.third_West_img);
        thirdNorthImgView = (ImageView) rootView.findViewById(R.id.third_north_img);
        thirdSouthImgView = (ImageView) rootView.findViewById(R.id.third_South_img);
        thirdCentralImgView = (ImageView) rootView.findViewById(R.id.third_central_img);
        fourthEastImgView = (ImageView) rootView.findViewById(R.id.fourth_east_img);
        fourthWestImgView = (ImageView) rootView.findViewById(R.id.fourth_West_img);
        fourthCentralImgView = (ImageView) rootView.findViewById(R.id.fourth_central_img);
        fourthSouthImgView = (ImageView) rootView.findViewById(R.id.fourth_South_img);
        fourthNorthImgView = (ImageView) rootView.findViewById(R.id.fourth_north_img);

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

    /**
     * User Interface is obtained by this method.
     *
     * @param currentDayWeatherList
     */
    public void updateUI(List<? extends Weather> currentDayWeatherList) {

        for(Object object : currentDayWeatherList) {
            CurrentDayWeather dayWeather = null;
            if (object instanceof CurrentDayWeather) {
                dayWeather = (CurrentDayWeather) object;

                mainForecastImgView.setImageResource(WeatherUtils.getImageResource(dayWeather.getWeather()));
                mainForecastTv.setText(dayWeather.getForecast());

                Area firstPeriod = null;
                Area secondPeriod = null;
                Area thirdPeriod = null;
                Area fourthPeriod = null;

                String dayOne = "";
                String dayTwo = "";
                String dayThree = "";
                String dayFour = "";

                int count = 1;
                for (Map.Entry<String,Area> entry : dayWeather.getAreaMap().entrySet()) {
                    if (count == 1) {
                        dayOne = entry.getKey();
                        firstPeriod = entry.getValue();
                    } else if (count == 2) {
                        dayTwo = entry.getKey();
                        secondPeriod = entry.getValue();
                    } else if (count == 3) {
                        dayThree = entry.getKey();
                        thirdPeriod = entry.getValue();
                    } else if (count == 4) {
                        dayFour = entry.getKey();
                        fourthPeriod = entry.getValue();
                    }
                    count++;
                }



                if (firstPeriod != null) {
                    firstEastTv.setText(WeatherUtils.getForecastFromCode(firstPeriod.getEast()));
                    firstWestTv.setText(WeatherUtils.getForecastFromCode(firstPeriod.getWest()));
                    firstCentralTv.setText(WeatherUtils.getForecastFromCode(firstPeriod.getCentral()));
                    firstNorthTv.setText(WeatherUtils.getForecastFromCode(firstPeriod.getNorth()));
                    firstSouthTv.setText(WeatherUtils.getForecastFromCode(firstPeriod.getSouth()));
                    firstEastImgView.setImageResource(WeatherUtils.getImageResource(firstPeriod.getEast()));
                    firstWestImgView.setImageResource(WeatherUtils.getImageResource(firstPeriod.getWest()));
                    firstCentralImgView.setImageResource(WeatherUtils.getImageResource(firstPeriod.getCentral()));
                    firstNorthImgView.setImageResource(WeatherUtils.getImageResource(firstPeriod.getNorth()));
                    firstSouthImgView.setImageResource(WeatherUtils.getImageResource(firstPeriod.getSouth()));
                    firstTv.setText(dayOne.toUpperCase());
                } else {
                    dayFirstLayout.setVisibility(View.GONE);
                    firstTv.setVisibility(View.GONE);
                }



                if (secondPeriod != null) {
                    secondEastTv.setText(WeatherUtils.getForecastFromCode(secondPeriod.getEast()));
                    secondWestTv.setText(WeatherUtils.getForecastFromCode(secondPeriod.getWest()));
                    secondSouthTv.setText(WeatherUtils.getForecastFromCode(secondPeriod.getSouth()));
                    secondNorthTv.setText(WeatherUtils.getForecastFromCode(secondPeriod.getNorth()));
                    secondCentralTv.setText(WeatherUtils.getForecastFromCode(secondPeriod.getCentral()));
                    secondEastImgView.setImageResource(WeatherUtils.getImageResource(secondPeriod.getEast()));
                    secondWestImgView.setImageResource(WeatherUtils.getImageResource(secondPeriod.getWest()));
                    secondSouthImgView.setImageResource(WeatherUtils.getImageResource(secondPeriod.getSouth()));
                    secondNorthImgView.setImageResource(WeatherUtils.getImageResource(secondPeriod.getNorth()));
                    secondCentralImgView.setImageResource(WeatherUtils.getImageResource(secondPeriod.getCentral()));
                    secondTv.setText(dayTwo.toUpperCase());
                } else {
                    daySecondLayout.setVisibility(View.GONE);
                    secondTv.setVisibility(View.GONE);
                }


                if (thirdPeriod != null) {
                    thirdEastTv.setText(WeatherUtils.getForecastFromCode(thirdPeriod.getEast()));
                    thirdWestTv.setText(WeatherUtils.getForecastFromCode(thirdPeriod.getWest()));
                    thirdNorthTv.setText(WeatherUtils.getForecastFromCode(thirdPeriod.getNorth()));
                    thirdSouthTv.setText(WeatherUtils.getForecastFromCode(thirdPeriod.getSouth()));
                    thirdCentralTv.setText(WeatherUtils.getForecastFromCode(thirdPeriod.getCentral()));
                    thirdEastImgView.setImageResource(WeatherUtils.getImageResource(thirdPeriod.getEast()));
                    thirdWestImgView.setImageResource(WeatherUtils.getImageResource(thirdPeriod.getWest()));
                    thirdNorthImgView.setImageResource(WeatherUtils.getImageResource(thirdPeriod.getNorth()));
                    thirdSouthImgView.setImageResource(WeatherUtils.getImageResource(thirdPeriod.getSouth()));
                    thirdCentralImgView.setImageResource(WeatherUtils.getImageResource(thirdPeriod.getCentral()));
                    thirdTv.setText(dayThree.toUpperCase());
                } else {
                    dayThirdLayout.setVisibility(View.GONE);
                    thirdTv.setVisibility(View.GONE);
                }


                if (fourthPeriod != null) {
                    fourthEastTv.setText(WeatherUtils.getForecastFromCode(fourthPeriod.getEast()));
                    fourthWestTv.setText(WeatherUtils.getForecastFromCode(fourthPeriod.getWest()));
                    fourthNorthTv.setText(WeatherUtils.getForecastFromCode(fourthPeriod.getNorth()));
                    fourthSouthTv.setText(WeatherUtils.getForecastFromCode(fourthPeriod.getSouth()));
                    fourthCentralTv.setText(WeatherUtils.getForecastFromCode(fourthPeriod.getCentral()));
                    fourthEastImgView.setImageResource(WeatherUtils.getImageResource(fourthPeriod.getEast()));
                    fourthWestImgView.setImageResource(WeatherUtils.getImageResource(fourthPeriod.getWest()));
                    fourthNorthImgView.setImageResource(WeatherUtils.getImageResource(fourthPeriod.getNorth()));
                    fourthSouthImgView.setImageResource(WeatherUtils.getImageResource(fourthPeriod.getSouth()));
                    fourthCentralImgView.setImageResource(WeatherUtils.getImageResource(fourthPeriod.getCentral()));
                    fourthTv.setText(dayFour.toUpperCase());
                } else {
                    dayFourthLayout.setVisibility(View.GONE);
                    fourthTv.setVisibility(View.GONE);
                }
            }

        }

    }

}
