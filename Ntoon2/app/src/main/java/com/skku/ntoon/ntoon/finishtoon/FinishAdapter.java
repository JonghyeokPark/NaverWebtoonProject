package com.skku.ntoon.ntoon.finishtoon;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.skku.ntoon.ntoon.Util.SideFragment;

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

        Log.i("FinishPage", "FinishAdapter started!");
        switch (position) {
            case 0:
                return new SideFragment("에피소드",1);
            case 1:
                return new SideFragment("옴니버스",1);
            case 2:
                return new SideFragment("스토리",1);
            case 3:
                return new SideFragment("일상",1);
            case 4:
                return new SideFragment("개그",1);
            case 5:
                return new SideFragment("판타지",1);
            case 6:
                return new SideFragment("액션",1);
            case 7:
                return new SideFragment("드라마",1);
            case 8:
                return new SideFragment("순정",1);
            case 9:
                return new SideFragment("감성",1);
            case 10:
                return new SideFragment("스릴러",1);
            case 11:
                return new SideFragment("시대극",1);
            case 12:
                return new SideFragment("스포츠",1);
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