package com.skku.ntoon.ntoon.webtoon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skku.ntoon.ntoon.R;

/**
 * Created by kimhyojin on 15. 12. 31..
 */
public class WebtoonFragment extends android.app.Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_webtoon, container, false);
        return rootView;
    }
}
