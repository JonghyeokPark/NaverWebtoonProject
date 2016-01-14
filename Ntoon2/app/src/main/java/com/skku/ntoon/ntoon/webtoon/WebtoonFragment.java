package com.skku.ntoon.ntoon.webtoon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skku.ntoon.ntoon.R;

/**
 * Created by kimhyojin on 15. 12. 31..
 */
public class WebtoonFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_webtoon, container, false);
        TabLayout tabLayout = (TabLayout)rootView.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("월"));
        tabLayout.addTab(tabLayout.newTab().setText("화"));
        tabLayout.addTab(tabLayout.newTab().setText("수"));
        tabLayout.addTab(tabLayout.newTab().setText("목"));
        tabLayout.addTab(tabLayout.newTab().setText("금"));
        tabLayout.addTab(tabLayout.newTab().setText("토"));
        tabLayout.addTab(tabLayout.newTab().setText("일"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setSelectedTabIndicatorColor(0xFF49AA0C);

        final ViewPager viewPager = (ViewPager)rootView.findViewById(R.id.vpPager);

        viewPager.setAdapter(new MyPagerAdapter(getChildFragmentManager()));
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

