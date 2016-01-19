package com.skku.ntoon.ntoon.finishtoon;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.skku.ntoon.ntoon.R;

public class GenreAdapter extends BaseAdapter
{
    private Activity activity;
    private ArrayList<FinishData> fdata;

    public GenreAdapter(Activity activity, ArrayList<FinishData> fdata) {
        super();
        this.activity = activity;
        this.fdata = fdata;
    }

    @Override
    public int getCount() { return fdata.size(); }

    @Override
    public Object getItem(int position) {
        return fdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        ImageLoader imageLoader = ImageLoader.getInstance();


        // TODO Auto-generated method stub
        LayoutInflater inflator = activity.getLayoutInflater();
        convertView = inflator.inflate(R.layout.adapter_webtoon, null);

        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView author = (TextView) convertView.findViewById(R.id.author);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);

        name.setText(fdata.get(position).getName());
        author.setText(fdata.get(position).getAuthor());
        imageLoader.displayImage(fdata.get(position).getThumbnail(), imageView);
        return convertView;
    }
}