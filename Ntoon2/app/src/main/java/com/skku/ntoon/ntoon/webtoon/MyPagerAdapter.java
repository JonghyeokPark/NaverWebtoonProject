package com.skku.ntoon.ntoon.webtoon;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by CNWMAC on 16. 1. 5..
 */
public class MyPagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 7;

    public MyPagerAdapter(FragmentManager childFragmentManager) {
        super(childFragmentManager);
    }



    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        switch (position) {
            case 0:
                return DayFragment.newInstance(0, "Page # 1");
            case 1:
                return DayFragment.newInstance(1, "Page # 2");
            case 2:
                return DayFragment.newInstance(2, "Page # 3");
            case 3:
                return DayFragment.newInstance(3, "Page # 4");
            case 4:
                return DayFragment.newInstance(4, "Page # 5");
            case 5:
                return DayFragment.newInstance(5, "Page # 6");
            case 6:
                return DayFragment.newInstance(6, "Page # 7");
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }

}