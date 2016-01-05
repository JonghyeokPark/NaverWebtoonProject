package com.skku.ntoon.ntoon.mypage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jonghyeok on 2016. 1. 4..
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table user("+
                "uid integer primary key autoincrement, "+
                "wid integer, "+
                "wname text, "+
                "islike text, "+
                "number text, "+
                "date Timestamp DATETIME DEFAULT CURRENT_TIMESTAMP);";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "drop table if exists user";
        db.execSQL(sql);

        onCreate(db);
    }

}
