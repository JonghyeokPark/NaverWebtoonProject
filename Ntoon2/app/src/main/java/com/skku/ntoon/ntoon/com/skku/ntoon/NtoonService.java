package com.skku.ntoon.ntoon.com.skku.ntoon;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.skku.ntoon.ntoon.R;


/**
 * Created by kimhyojin on 16. 1. 5..
 * https://github.com/nickfox/Update-Android-UI-from-a-Service/blob/master/src/com/websmithing/broadcasttest/BroadcastService.java
 */
public class NtoonService extends Service {

    public static final String BROADCAST_ACTION = "com.example.tracking.updateprogress";
    Intent intent;
    private final Handler handler = new Handler();
    int counter = 0;

    @Override
    public void onCreate() {

        intent = new Intent(BROADCAST_ACTION);
        sendBroadcast(intent);
        super.onCreate();

    }

    @Override
    public void onStart(Intent intent, int startId) {
        handler.removeCallbacks(sendUpdatesToUI);
        handler.postDelayed(sendUpdatesToUI, 1000); // 1 second
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        handler.removeCallbacks(sendUpdatesToUI);
        super.onDestroy();
    }

    private Runnable sendUpdatesToUI = new Runnable() {
        public void run() {
            DisplayLoggingInfo();
            handler.postDelayed(this, 2000); // 10 seconds
        }
    };

    private void DisplayLoggingInfo() {
        String a = null;
        if(counter == 0){
            a = "안녕하세요! N툰입니다";
            counter++;
        }else if(counter == 1){
            a = "오늘의 추천웹툰은 덴마에요";
            counter--;
        }
        intent.putExtra("string", a);
        sendBroadcast(intent);
    }

}
