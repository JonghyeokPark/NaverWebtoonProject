package com.skku.ntoon.ntoon.finishtoon;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.skku.ntoon.ntoon.webtoon.*;
import com.skku.ntoon.ntoon.webtoon.DayFragment;

/**
 * Created by CNWMAC on 16. 1. 5..
 */
public class MyPagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 13;

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
                return com.skku.ntoon.ntoon.finishtoon.DayFragment.newInstance(0, "Page # 1");
            case 1:
                return com.skku.ntoon.ntoon.webtoon.DayFragment.newInstance(1, "Page # 2");
            case 2:
                return com.skku.ntoon.ntoon.webtoon.DayFragment.newInstance(2, "Page # 3");
            case 3:
                return com.skku.ntoon.ntoon.webtoon.DayFragment.newInstance(3, "Page # 4");
            case 4:
                return com.skku.ntoon.ntoon.webtoon.DayFragment.newInstance(4, "Page # 5");
            case 5:
                return com.skku.ntoon.ntoon.webtoon.DayFragment.newInstance(5, "Page # 6");
            case 6:
                return com.skku.ntoon.ntoon.webtoon.DayFragment.newInstance(6, "Page # 7");
            case 7:
                return com.skku.ntoon.ntoon.webtoon.DayFragment.newInstance(7, "Page # 8");
            case 8:
                return com.skku.ntoon.ntoon.webtoon.DayFragment.newInstance(8, "Page # 9");
            case 9:
                return com.skku.ntoon.ntoon.webtoon.DayFragment.newInstance(9, "Page # 10");
            case 10:
                return com.skku.ntoon.ntoon.webtoon.DayFragment.newInstance(10, "Page # 11");
            case 11:
                return com.skku.ntoon.ntoon.webtoon.DayFragment.newInstance(11, "Page # 12");
            case 12:
                return DayFragment.newInstance(12, "Page # 13");
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