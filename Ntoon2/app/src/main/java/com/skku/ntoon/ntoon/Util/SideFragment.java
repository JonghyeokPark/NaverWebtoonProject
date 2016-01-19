package com.skku.ntoon.ntoon.Util;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.skku.ntoon.ntoon.ListPage.ListFragment;
import com.skku.ntoon.ntoon.R;
import com.skku.ntoon.ntoon.Util.WebtoonAdapter;
import com.skku.ntoon.ntoon.Util.WebtoonArray;
import com.skku.ntoon.ntoon.Util.WebtoonData;
import com.skku.ntoon.ntoon.Util.WebtoonList;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by CNWMAC on 16. 1. 5..
 */
public class SideFragment extends Fragment {
    // Store instance variables
    public static final String TAG = "YOUR-TAG-NAME";
    private String title;
    GridView gridView;
    RelativeLayout footerView;
    private int mLastFirstVisibleItem;
    Animation translateUpAnim;
    Animation translateDownAnim;
    private boolean mIsScrollingUp,mIsMenuBarUp=true;

    private String type;
    private String isend;
    private WebtoonArray fdata;
    private WebtoonAdapter gadapter;
    private GridView list_webtoon;

    ArrayList<HashMap<String,String>> webtoonList = new WebtoonList().getWebtoonList();

    public SideFragment(String type, int isend) {
        this.type = type;
        this.isend = String.valueOf(isend);
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_fragment_webtoon, container, false);

        HashMap<String,String> webtoon;
        fdata = new WebtoonArray();

        for(int i = 0;i < webtoonList.size(); i++){
            webtoon = webtoonList.get(i);

            if(webtoon.get("isend").equals(isend)) {
                Log.d("==============", "1");
                if(isend.equals("1")) {
                    if (type.equals(webtoon.get("genre"))) {
                        Log.d("==============", "inside");

                        Log.d("WEBTOON name", webtoon.get("name"));
                        Log.d("WEBTOON author", webtoon.get("author"));
                        Log.d("WEBTOON isend",webtoon.get("isend"));
                        Log.d("WEBTOON genre",webtoon.get("genre"));
                        Log.d("WEBTOON day","a"+webtoon.get("day")+"a");
                        Log.d("WEBTOON star", webtoon.get("star"));

                        fdata.add(new WebtoonData(
                                webtoon.get("name"),webtoon.get("author"),webtoon.get("star"),webtoon.get("thumbnail"),
                                webtoon.get("intro"), webtoon.get("wid")));
                    }
                }
                else {
                    if (type.equals(webtoon.get("day"))) {
                        Log.d("==============", "inside");

                        Log.d("WEBTOON name", webtoon.get("name"));
                        Log.d("WEBTOON author", webtoon.get("author"));
                        Log.d("WEBTOON isend",webtoon.get("isend"));
                        Log.d("WEBTOON genre",webtoon.get("genre"));
                        Log.d("WEBTOON day","a"+webtoon.get("day")+"a");
                        Log.d("WEBTOON star", webtoon.get("star"));

                        fdata.add(new WebtoonData(
                                webtoon.get("name"),webtoon.get("author"),webtoon.get("star"),webtoon.get("thumbnail"),
                                webtoon.get("intro"), webtoon.get("wid")));
                    }
                }
            }
        }

        translateUpAnim = AnimationUtils.loadAnimation(getContext(), R.anim.translate_up);
        translateDownAnim = AnimationUtils.loadAnimation(getContext(), R.anim.translate_down);
        SlidingPageAnimationListener animListener = new SlidingPageAnimationListener();
        translateUpAnim.setAnimationListener(animListener);
        translateDownAnim.setAnimationListener(animListener);

        list_webtoon = (GridView) view.findViewById(R.id.gridView);
        gadapter = new WebtoonAdapter(getActivity(),fdata);
        list_webtoon.setAdapter(gadapter);
        footerView = (RelativeLayout) view.findViewById(R.id.footer_gridview);

        list_webtoon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                WebtoonData fd = fdata.get(pos);
                FragmentManager fm = getParentFragment().getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.container, new ListFragment(fd.getName()));
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        list_webtoon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int pos, long iD) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.custom_dialog);

                TextView text = (TextView) dialog.findViewById(R.id.textView);
                text.setText("sample");
                ImageView image = (ImageView) dialog.findViewById(R.id.imageView);
                image.setImageResource(R.drawable.sample);
                dialog.show();
                return true;
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