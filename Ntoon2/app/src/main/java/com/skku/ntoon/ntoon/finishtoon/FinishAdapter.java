package com.skku.ntoon.ntoon.finishtoon;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by luusoo on 16. 1. 5..
 */
public class FinishAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 13;

    public FinishAdapter(FragmentManager childFragmentManager) {
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
                return GenreFragment.newInstance(0, "Page # 1");
            case 1:
                return GenreFragment.newInstance(1, "Page # 2");
            case 2:
                return GenreFragment.newInstance(2, "Page # 3");
            case 3:
                return GenreFragment.newInstance(3, "Page # 4");
            case 4:
                return GenreFragment.newInstance(4, "Page # 5");
            case 5:
                return GenreFragment.newInstance(5, "Page # 6");
            case 6:
                return GenreFragment.newInstance(6, "Page # 7");
            case 7:
                return GenreFragment.newInstance(7, "Page # 8");
            case 8:
                return GenreFragment.newInstance(8, "Page # 9");
            case 9:
                return GenreFragment.newInstance(9, "Page # 10");
            case 10:
                return GenreFragment.newInstance(10, "Page # 11");
            case 11:
                return GenreFragment.newInstance(11, "Page # 12");
            case 12:
                return GenreFragment.newInstance(12, "Page # 13");
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