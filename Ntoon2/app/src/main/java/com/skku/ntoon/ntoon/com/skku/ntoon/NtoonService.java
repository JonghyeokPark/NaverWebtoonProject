package com.skku.ntoon.ntoon.com.skku.ntoon;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.skku.ntoon.ntoon.R;

/**
 * Created by kimhyojin on 16. 1. 5..
 */
public class NtoonService extends Service {

    public static final String BROADCAST_ACTION = "com.example.tracking.updateprogress";
    Intent intent;

    @Override
    public void onCreate() {

        intent = new Intent(BROADCAST_ACTION);
        sendBroadcast(intent);
        DisplayLoggingInfo("안녕하세요! 저는 N툰입니다");
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void DisplayLoggingInfo(String string) {

        intent.putExtra("string", string);
        sendBroadcast(intent);
    }

}
