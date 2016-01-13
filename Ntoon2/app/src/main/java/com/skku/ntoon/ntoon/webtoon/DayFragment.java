package com.skku.ntoon.ntoon.webtoon;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.skku.ntoon.ntoon.R;

/**
 * Created by CNWMAC on 16. 1. 5..
 */
public class DayFragment extends Fragment {
    // Store instance variables
    public static final String TAG = "YOUR-TAG-NAME";
    private String title;
    private int page;
    GridView gridView;
    RelativeLayout footerView;
    private int mLastFirstVisibleItem;
    Animation translateUpAnim;
    Animation translateDownAnim;
    private boolean mIsScrollingUp,mIsMenuBarUp=true;



    static final String[] numbers = new String[] {
            "A", "B", "C", "D", "E",
            "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O",
            "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"};

    // newInstance constructor for creating fragment with arguments
    public static DayFragment newInstance(int page, String title) {
        DayFragment fragmentFirst = new DayFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }


private OnScrollListener gridViewScrollListener = new OnScrollListener() {
        public void onScrollStateChanged(AbsListView view, int scrollState) {

            if (view.getId() == gridView.getId()) {
                final int currentFirstVisibleItem = gridView.getFirstVisiblePosition();

                switch (scrollState) {
                case OnScrollListener.SCROLL_STATE_IDLE :

                    break;
                case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL :
                    if (currentFirstVisibleItem > mLastFirstVisibleItem) {
                        Log.d(TAG, "touch Scrolling down...");
                        mIsScrollingUp = false;
                        if(mIsMenuBarUp == false){
                            footerView.setVisibility(View.VISIBLE);
                            footerView.startAnimation(translateUpAnim);
                        }
                    } else if (currentFirstVisibleItem < mLastFirstVisibleItem) {
                        Log.d(TAG, "touch Scrolling up...");
                        mIsScrollingUp = true;
                        if(mIsMenuBarUp == true){
                            footerView.startAnimation(translateDownAnim);
                        }
                    }

                    mLastFirstVisibleItem = currentFirstVisibleItem;
                    break;
                case OnScrollListener.SCROLL_STATE_FLING :
                    if (currentFirstVisibleItem > mLastFirstVisibleItem) {
                        Log.d(TAG, "flying Scrolling down...");
                        mIsScrollingUp = false;
                        if(mIsMenuBarUp == false){
                            footerView.setVisibility(View.VISIBLE);
                            footerView.startAnimation(translateUpAnim);
                        }
                    } else if (currentFirstVisibleItem < mLastFirstVisibleItem) {
                        Log.d(TAG, "flying Scrolling up...");
                        mIsScrollingUp = true;
                        if(mIsMenuBarUp == true){
                            footerView.startAnimation(translateDownAnim);
                        }
                    }

                    mLastFirstVisibleItem = currentFirstVisibleItem;

                    break;
            }


            }
        }

        public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
        }
    };


    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_webtoon, container, false);
        TextView tvLabel = (TextView) view.findViewById(R.id.tvLabel);
        tvLabel.setText(page + " -- " + title);

        translateUpAnim = AnimationUtils.loadAnimation(getContext(), R.anim.translate_up);
        translateDownAnim = AnimationUtils.loadAnimation(getContext(), R.anim.translate_down);
        SlidingPageAnimationListener animListener = new SlidingPageAnimationListener();
        translateUpAnim.setAnimationListener(animListener);
        translateDownAnim.setAnimationListener(animListener);


        footerView = (RelativeLayout) view.findViewById(R.id.footer_gridview);
        gridView = (GridView) view.findViewById(R.id.gridView1);


        gridView.setAdapter(new ImageAdapter(getContext(), numbers));
        gridView.setOnScrollListener(gridViewScrollListener);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getContext(),((TextView) v.findViewById(R.id.textView1)).getText(), Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
    private class SlidingPageAnimationListener implements AnimationListener {

        public void onAnimationEnd(Animation animation) {
            if (mIsMenuBarUp) {
                footerView.setVisibility(View.INVISIBLE);
                mIsMenuBarUp = false;
            } else {
                mIsMenuBarUp = true;
            }
        }

        public void onAnimationRepeat(Animation animation) {

        }

        public void onAnimationStart(Animation animation) {

        }

    }

}