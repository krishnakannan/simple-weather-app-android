package io.github.krishnakannan.simpleweatherapp.Adapter;

/**
 * Created by Krish on 17/07/16.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import io.github.krishnakannan.simpleweatherapp.Fragment.DayFragment;
import io.github.krishnakannan.simpleweatherapp.Fragment.HomeFragment;
import io.github.krishnakannan.simpleweatherapp.Fragment.WeekFragment;
import io.github.krishnakannan.simpleweatherapp.Util.AppConstants;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    private HomeFragment homeFragment;

    private DayFragment dayFragment;

    private WeekFragment weekFragment;

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return HomeFragment.newInstance();
            case 1:
                return DayFragment.newInstance();
            case 2:
                return WeekFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "SECTION 1";
            case 1:
                return "SECTION 2";
            case 2:
                return "SECTION 3";
        }
        return null;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment createdFragment = (Fragment) super.instantiateItem(container, position);
        switch (position) {
            case 0:
                homeFragment = (HomeFragment) createdFragment;
                break;
            case 1:
                dayFragment = (DayFragment) createdFragment;
                break;
            case 2:
                weekFragment = (WeekFragment) createdFragment;
                break;
        }
        return createdFragment;
    }

    public Fragment getFragment(String tag) {
        if (tag.equals(AppConstants.HOME)) {
           return homeFragment;
        } else if (tag.equals(AppConstants.DAY)) {
            return dayFragment;
        } else if (tag.equals(AppConstants.WEEK)) {
            return weekFragment;
        }
        return null;
    }
}
