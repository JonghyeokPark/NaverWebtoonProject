package com.skku.ntoon.ntoon.mypage;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.skku.ntoon.ntoon.R;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Jonghyeok on 2016. 1. 4..
 */
public class MypageListAdapter extends BaseAdapter {

    Context context;
    int listitem;
    ArrayList<MypageData>data;
    LayoutInflater layoutInflater;

    public MypageListAdapter(Activity activity, int listitem, ArrayList<MypageData> data) {

        this.context = activity.getApplicationContext();
        this.listitem = listitem;
        this.data = data;
        layoutInflater = (LayoutInflater) this.context.getSystemService(this.context.LAYOUT_INFLATER_SERVICE);

    }
/*
    public void MypageListAdapter(Context context, int listitem, ArrayList<MypageData> data)
    {
        this.context = context;
        this.listitem = listitem;
        this.data = data;
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }
*/
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null)
        {
            convertView = layoutInflater.inflate(listitem,parent,false);
        }

        TextView wnameText = (TextView) convertView.findViewById(R.id.wname_tv);
        TextView numberText = (TextView) convertView.findViewById(R.id.number_tv);
        TextView dateText = (TextView) convertView.findViewById(R.id.date_tv);

        wnameText.setText(data.get(position).wname);
        if(data.get(position).islike.equals("1")) {
            String strColor = "#ff0000";
            wnameText.setTextColor(Color.parseColor(strColor));
        }

        numberText.setText(data.get(position).number);
        dateText.setText(data.get(position).date);

        return convertView;
    }
}
