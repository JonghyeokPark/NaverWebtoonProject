package com.skku.ntoon.ntoon.ListPage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.skku.ntoon.ntoon.R;

import java.util.ArrayList;

/**
 * Created by luusoo on 16. 1. 5..
 */
public class ListFragment extends Fragment {
    // Store instance variables
    private ArrayList<ListData> ldata;
    private ListAdapter ladapter;
    private ListView list_webtoon;
    private String title;
    private int page;
    String webtoon_name;

    int flag = 1;

    public ListFragment(String webtoon_name){
        this.webtoon_name = webtoon_name;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //page = getArguments().getInt("someInt", 0);
        //title = getArguments().getString("someTitle");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        ldata = new ArrayList<ListData>();

        ldata.add(new ListData(webtoon_name,webtoon_name,webtoon_name,webtoon_name));
        ldata.add(new ListData(webtoon_name,webtoon_name,webtoon_name,webtoon_name));
        ldata.add(new ListData(webtoon_name,webtoon_name,webtoon_name,webtoon_name));
        ldata.add(new ListData(webtoon_name,webtoon_name,webtoon_name,webtoon_name));

        list_webtoon = (ListView) view.findViewById(R.id.listView);
        ladapter = new ListAdapter(getActivity(),ldata);
        list_webtoon.setAdapter(ladapter);

        list_webtoon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {

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