package com.skku.ntoon.ntoon.mypage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.skku.ntoon.ntoon.R;

import java.util.ArrayList;

/**
 * Created by kimhyojin on 15. 12. 31..
 */
public class MypageFragment extends android.support.v4.app.Fragment {


    @Nullable
    SQLiteDatabase db;
    DataBaseHelper helper;
    ArrayList<MypageData> data = new ArrayList<MypageData>();
    public static final String MYPAGE_LOG = "MYPAGE";
    int flag=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mypage, container, false);
        helper = new DataBaseHelper(getActivity(), "user.db", null, 1);

        // dummy data
        if(flag==0)
        {
            flag=1;
            insert(1, "마음의 소리", "1", "1000");
            insert(2, "연애 혁명", "0", "80");
            insert(3, "오민혁 단편선", "1", "3");
        }

        db = helper.getReadableDatabase();
        Cursor c =db.query("user", null, null, null, null, null, null);

        while (c.moveToNext())
        {
            MypageData mpData;

            int wid = c.getInt(c.getColumnIndex("wid"));
            String wname = c.getString(c.getColumnIndex("wname"));
            String islike = c.getString(c.getColumnIndex("islike"));
            String number = c.getString(c.getColumnIndex("number"));
            String date = c.getString(c.getColumnIndex("date"));

            mpData = new MypageData(wid, wname, islike, number, date);
            data.add(mpData);
        }

        ListView list = (ListView) rootView.findViewById(R.id.mypage_lv);
        MypageListAdapter adapter = new MypageListAdapter(getActivity(), R.layout.list_item_mypage, data);
        list.setAdapter(adapter);

        return rootView;
    }

    public void insert(int wid, String wname, String islike, String number)
    {
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("wid",wid);
        values.put("wname",wname);
        values.put("islike",islike);
        values.put("number",number);

        db.insert("user", null, values);
    }

    public void delete(int wid)
    {
        db = helper.getWritableDatabase();
        db.delete("user", "wid=?", new String[]{Integer.toString(wid)});

        Log.i(MYPAGE_LOG, String.valueOf(wid) + "is deleted!");
    }

    public void select()
    {
        db = helper.getReadableDatabase();
        Cursor c =db.query("user", null, null, null, null, null, null);

        while (c.moveToNext())
        {
            String wname = c.getString(c.getColumnIndex("wname"));
            String islike = c.getString(c.getColumnIndex("islike"));
            String number = c.getString(c.getColumnIndex("number"));
            String date = c.getString(c.getColumnIndex("date"));

            Log.i(MYPAGE_LOG, "wname: ["+ wname +"] "+
                            "islike: ["+ islike +"] "+
                            "number: ["+ number +"] "+
                            "date: ["+ date + "] "
            );
        }
    }

}
