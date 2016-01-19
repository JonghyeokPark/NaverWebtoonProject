package com.skku.ntoon.ntoon.webtoon;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.skku.ntoon.ntoon.Util.SideFragment;

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
                return new SideFragment("월",0);
            case 1:
                return new SideFragment("화",0);
            case 2:
                return new SideFragment("수",0);
            case 3:
                return new SideFragment("목",0);
            case 4:
                return new SideFragment("금",0);
            case 5:
                return new SideFragment("토",0);
            case 6:
                return new SideFragment("일",0);
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