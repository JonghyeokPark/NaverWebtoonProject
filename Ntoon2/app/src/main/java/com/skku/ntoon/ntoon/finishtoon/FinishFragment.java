package com.skku.ntoon.ntoon.finishtoon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skku.ntoon.ntoon.R;

/**
 * Created by kimhyojin on 15. 12. 31..
 */
public class FinishFragment extends android.support.v4.app.Fragment{
    FragmentPagerAdapter adapterViewPager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_webtoon, container, false);
        TabLayout tabLayout = (TabLayout)rootView.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("에피소드"));
        tabLayout.addTab(tabLayout.newTab().setText("옴니버스"));
        tabLayout.addTab(tabLayout.newTab().setText("스토리"));
        tabLayout.addTab(tabLayout.newTab().setText("일상"));
        tabLayout.addTab(tabLayout.newTab().setText("개그"));
        tabLayout.addTab(tabLayout.newTab().setText("판타지"));
        tabLayout.addTab(tabLayout.newTab().setText("액션"));
        tabLayout.addTab(tabLayout.newTab().setText("드라마"));
        tabLayout.addTab(tabLayout.newTab().setText("순정"));
        tabLayout.addTab(tabLayout.newTab().setText("감성"));
        tabLayout.addTab(tabLayout.newTab().setText("스릴러"));
        tabLayout.addTab(tabLayout.newTab().setText("시대극"));
        tabLayout.addTab(tabLayout.newTab().setText("스포츠"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setSelectedTabIndicatorColor(0xFF49AA0C);

        final ViewPager viewPager = (ViewPager)rootView.findViewById(R.id.vpPager);

        viewPager.setAdapter(new com.skku.ntoon.ntoon.finishtoon.MyPagerAdapter(getFragmentManager()));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return rootView;
    }
}

