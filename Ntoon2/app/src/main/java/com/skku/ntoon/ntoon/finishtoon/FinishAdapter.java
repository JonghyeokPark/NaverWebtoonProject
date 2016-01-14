package com.skku.ntoon.ntoon.finishtoon;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

/**
 * Created by luusoo on 16. 1. 5..
 */
public class FinishAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 13;

    public FinishAdapter(FragmentManager childFragmentManager) {
        super(childFragmentManager);

        Log.i("adapter", "started!");
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public android.support.v4.app.Fragment getItem(int position) {

        GenreFragment genreFragment;
        Log.i("FinishPage", "FinishAdapter started!");
        switch (position) {
            case 0:
                return new GenreFragment(0);
            case 1:
                return new GenreFragment(1);
            case 2:
                return new GenreFragment(2);
            case 3:
                return new GenreFragment(3);
            case 4:
                return new GenreFragment(4);
            case 5:
                return new GenreFragment(5);
            case 6:
                return new GenreFragment(6);
            case 7:
                return new GenreFragment(7);
            case 8:
                return new GenreFragment(8);
            case 9:
                return new GenreFragment(9);
            case 10:
                return new GenreFragment(10);
            case 11:
                return new GenreFragment(11);
            case 12:
                return new GenreFragment(12);
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