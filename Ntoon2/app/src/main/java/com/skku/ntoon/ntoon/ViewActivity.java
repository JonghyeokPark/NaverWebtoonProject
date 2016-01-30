package com.skku.ntoon.ntoon;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by luusoo on 2016-01-30.
 */
public class ViewActivity extends AppCompatActivity{
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        Intent intent = getIntent();
        String number = String.valueOf(intent.getStringExtra("number"));


    }
}
