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

import io.github.krishnakannan.simpleweatherapp.Model.CurrentWeekWeather;
import io.github.krishnakannan.simpleweatherapp.Model.Weather;
import io.github.krishnakannan.simpleweatherapp.R;
import io.github.krishnakannan.simpleweatherapp.Util.WeatherUtils;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WeekFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WeekFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeekFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public WeekFragment() {
        // Required empty public constructor
    }

    private TextView dayOne;
    private TextView dayTwo;
    private TextView dayThree;
    private TextView dayFour;

    private TextView dayOneTemperature;
    private TextView dayOneForecast;
    private ImageView dayOneForecastImage;

    private TextView dayTwoTemperature;
    private TextView dayTwoForecast;
    private ImageView dayTwoForecastImage;

    private TextView dayThreeTemperature;
    private TextView dayThreeForecast;
    private ImageView dayThreeForecastImage;

    private TextView dayFourTemperature;
    private TextView dayFourForecast;
    private ImageView dayFourForecastImage;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment WeekFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WeekFragment newInstance() {
        WeekFragment fragment = new WeekFragment();
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

        View rootView = inflater.inflate(R.layout.fragment_week, container, false);

        dayOne = (TextView) rootView.findViewById(R.id.dayone_forecast);
        dayTwo = (TextView) rootView.findViewById(R.id.daytwo_forecast);
        dayThree = (TextView) rootView.findViewById(R.id.daythree_forecast);
        dayFour = (TextView) rootView.findViewById(R.id.dayfour_forecast);

        dayOneForecast = (TextView) rootView.findViewById(R.id.dayone_forecast_textview);
        dayOneTemperature = (TextView) rootView.findViewById(R.id.dayone_temperature_textview);
        dayOneForecastImage = (ImageView) rootView.findViewById(R.id.dayone_img);

        dayTwoForecast = (TextView) rootView.findViewById(R.id.daytwo_forecast_textview);
        dayTwoTemperature = (TextView) rootView.findViewById(R.id.daytwo_temperature_textview);
        dayTwoForecastImage = (ImageView) rootView.findViewById(R.id.daytwo_img);

        dayThreeForecast = (TextView) rootView.findViewById(R.id.daythree_forecast_textview);
        dayThreeTemperature = (TextView) rootView.findViewById(R.id.daythree_temperature_textview);
        dayThreeForecastImage = (ImageView) rootView.findViewById(R.id.daythree_img);

        dayFourForecast = (TextView) rootView.findViewById(R.id.dayfour_forecast_textview);
        dayFourTemperature = (TextView) rootView.findViewById(R.id.dayfour_temperature_textview);
        dayFourForecastImage = (ImageView) rootView.findViewById(R.id.dayfour_img);

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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

    public void updateUI (List<? extends Weather> response) {

        Object dayOneObj = response.get(0);
        Object dayTwoObj = response.get(1);
        Object dayThreeObj = response.get(2);
        Object dayFourObj = response.get(3);

        if (dayOneObj instanceof CurrentWeekWeather) {
            dayOne.setText(((CurrentWeekWeather) dayOneObj).getDay());
            dayOneForecast.setText(((CurrentWeekWeather) dayOneObj).getForecast());
            dayOneTemperature.setText(((CurrentWeekWeather) dayOneObj).getTemperature());
            dayOneForecastImage.setImageResource(WeatherUtils.getImageResource(((CurrentWeekWeather) dayOneObj).getIcon()));
        }

        if (dayTwoObj instanceof CurrentWeekWeather) {
            dayTwo.setText(((CurrentWeekWeather) dayTwoObj).getDay());
            dayTwoForecast.setText(((CurrentWeekWeather) dayTwoObj).getForecast());
            dayTwoTemperature.setText(((CurrentWeekWeather) dayTwoObj).getTemperature());
            dayTwoForecastImage.setImageResource(WeatherUtils.getImageResource(((CurrentWeekWeather) dayTwoObj).getIcon()));
        }
        if (dayThreeObj instanceof CurrentWeekWeather) {
            dayThree.setText(((CurrentWeekWeather) dayThreeObj).getDay());
            dayThreeForecast.setText(((CurrentWeekWeather) dayThreeObj).getForecast());
            dayThreeTemperature.setText(((CurrentWeekWeather) dayThreeObj).getTemperature());
            dayThreeForecastImage.setImageResource(WeatherUtils.getImageResource(((CurrentWeekWeather) dayThreeObj).getIcon()));
        }

        if (dayFourObj instanceof CurrentWeekWeather) {
            dayFour.setText(((CurrentWeekWeather) dayFourObj).getDay());
            dayFourForecast.setText(((CurrentWeekWeather) dayFourObj).getForecast());
            dayFourTemperature.setText(((CurrentWeekWeather) dayFourObj).getTemperature());
            dayFourForecastImage.setImageResource(WeatherUtils.getImageResource(((CurrentWeekWeather) dayFourObj).getIcon()));
        }

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
}
