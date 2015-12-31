package com.skku.ntoon.ntoon.store;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skku.ntoon.ntoon.R;

/**
 * Created by kimhyojin on 15. 12. 31..
 */
public class StoreFragment extends android.app.Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_store, container, false);
        return rootView;
    }
}
