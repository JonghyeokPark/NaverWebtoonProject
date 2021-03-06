package com.skku.ntoon.ntoon.ListPage;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.skku.ntoon.ntoon.R;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter
{
    private Activity activity;
    private ArrayList<ListData> ldata;

    public ListAdapter(Activity activity, ArrayList<ListData> ldata) {
        super();
        this.activity = activity;
        this.ldata = ldata;
    }

    @Override
    public int getCount() {
        return ldata.size();
    }

    @Override
    public Object getItem(int position) {
        return ldata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflator = activity.getLayoutInflater();
        convertView = inflator.inflate(R.layout.adapter_list, null);

        ImageLoader imageLoader = ImageLoader.getInstance();

        TextView title = (TextView) convertView.findViewById(R.id.title);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);

        title.setText(ldata.get(position).getTitle());
        imageLoader.displayImage(ldata.get(position).getThumbnail(), imageView);

        return convertView;
    }
}