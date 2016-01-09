package com.skku.ntoon.ntoon.finishtoon;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.skku.ntoon.ntoon.ListPage.ListFragment;
import com.skku.ntoon.ntoon.R;
import com.skku.ntoon.ntoon.mypage.MypageFragment;

import java.util.ArrayList;

/**
 * Created by luusoo on 16. 1. 5..
 */
public class GenreFragment extends Fragment {
    // Store instance variables
    private ArrayList<FinishData> fdata;
    private GenreAdapter gadapter;
    private GridView list_webtoon;

    int flag = 1;

    // newInstance constructor for creating fragment with arguments
    public static GenreFragment newInstance(int page, String title) {
        GenreFragment fragmentFirst = new GenreFragment();
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
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_finish, container, false);

        fdata = new ArrayList<>();

        fdata.add(new FinishData("aa","aa","aa",1.0));

        list_webtoon = (GridView) view.findViewById(R.id.gridView);
        gadapter = new GenreAdapter(getActivity(),fdata);
        list_webtoon.setAdapter(gadapter);

        list_webtoon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                Fragment fr;

                //TODO: write webtoon_name
                fr = new ListFragment("webtoon_name");

                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.container, fr);
                fragmentTransaction.commit();
            }
        });

        list_webtoon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int pos, long iD) {
                return true;
            }
        });

        return view;
    }
}