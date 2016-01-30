package com.skku.ntoon.ntoon;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.skku.ntoon.ntoon.Util.JSONParser;
import com.skku.ntoon.ntoon.Util.WebtoonList;
import com.skku.ntoon.ntoon.com.skku.ntoon.NtoonService;
import com.skku.ntoon.ntoon.finishtoon.FinishFragment;
import com.skku.ntoon.ntoon.mypage.MypageFragment;
import com.skku.ntoon.ntoon.store.StoreFragment;
import com.skku.ntoon.ntoon.webtoon.WebtoonFragment;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PRODUCTS = "products";
    private static final String TAG_PID = "pid";
    private static final String TAG_WEBTOON = "webtoon";
    private static final String TAG_NAME = "name";


    ArrayList<HashMap<String,String>> webtoonList = new ArrayList<>();

    JSONParser jParser = new JSONParser();
    JSONArray webtoons = null;

    private static String url_all_products = "http://52.88.69.12/mysql_connect/show_webtoon.php";


    Button mypageBtn;
    Button webtoonBtn;
    Button finishBtn;
    Button storeBtn;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new LoadAllWebtoons().execute();


        mypageBtn = (Button) findViewById(R.id.mypageBtn_main);
        webtoonBtn = (Button) findViewById(R.id.webtoonBtn_main);
        finishBtn = (Button) findViewById(R.id.finishBtn_main);
        storeBtn = (Button) findViewById(R.id.storeBtn_main);

        mypageBtn.setOnClickListener(tabListener);
        webtoonBtn.setOnClickListener(tabListener);
        finishBtn.setOnClickListener(tabListener);
        storeBtn.setOnClickListener(tabListener);

        Fragment fr = new WebtoonFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.container, fr);
        fragmentTransaction.commit();

        intent = new Intent(this, NtoonService.class);
        startService(intent);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs()
                .build();
        ImageLoader.getInstance().init(config);
    }

    //Ntoon
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateUI(intent);
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        startService(intent);
        registerReceiver(broadcastReceiver, new IntentFilter(NtoonService.BROADCAST_ACTION));
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
        stopService(intent);
    }

    private void updateUI(Intent intent) {
        String string2 = intent.getStringExtra("string");

        TextView ntoon = (TextView) findViewById(R.id.NtoonsayTv_main);
        ntoon.setText(string2);
    }

    View.OnClickListener tabListener = new View.OnClickListener() {

        Fragment fr;

        @Override
        public void onClick(View v) {

            if (v == findViewById(R.id.mypageBtn_main)) {
                fr = new MypageFragment();
            } else if (v == findViewById(R.id.webtoonBtn_main)) {
                fr = new WebtoonFragment();
            } else if (v == findViewById(R.id.finishBtn_main)) {
                fr = new FinishFragment();
            } else if (v == findViewById(R.id.storeBtn_main)) {
                fr = new StoreFragment();
            }

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.container, fr);
            fragmentTransaction.commit();
        }
    };

    class LoadAllWebtoons extends AsyncTask<String, String, String > {

        @Override
        protected String doInBackground(String... args) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            Log.d("TAG : ", "LoadAllWebtoons 1");

            JSONObject json = jParser.makeHttpRequest(url_all_products, "GET", params);
            Log.d("TAG : ","LoadAllWebtoons 2");

            Log.d("TAG : ", json.toString());

            try {
                int success = json.getInt(TAG_SUCCESS);
                Log.d("TAG",String.valueOf(success));
                if(success == 1){
                    webtoons = json.getJSONArray(TAG_WEBTOON);

                    if(new WebtoonList().isEmpty(0)) {
                        for (int i = 0; i < webtoons.length(); i++) {
                            JSONObject c = webtoons.getJSONObject(i);
                        /* $product["wid"] = $row["wid"];
        $product["name"] = $row["name"];
        $product["star"] = $row["star"];
        $product["author"] = $row["author"];
        $product["genre"] = $row["genre"];
        $product["intro"] = $row["intro"];
        $product["day"] = $row["day"];
        $product["thumbnail"] = $row["thumbnail"];
        $product["likes"] = $row["likes"];
        $product["isend"] = $row["isend"];
        $product["isstop"] = $row["isstop"];
        $product["startdate"] = $row["startdate"];*/

                            String wid = c.getString("wid");
                            String name = c.getString("name");
                            String star = c.getString("star");
                            String author = c.getString("author");
                            String genre = c.getString("genre");
                            String intro = c.getString("intro");
                            String day = c.getString("day");
                            String thumbnail = c.getString("thumbnail");
                            String likes = c.getString("likes");
                            String isend = c.getString("isend");
                            String isstop = c.getString("isstop");
                            String startdate = c.getString("startdate");

                            //Log.d("TAG", name);
                            //Log.d("TAG", author);
                            Log.d("TAG", isend);
                            Log.d("TAG", day);


                            HashMap<String, String> map = new HashMap<>();
                            map.put("wid", wid);
                            map.put("name", name);
                            map.put("star", star);
                            map.put("author", author);
                            map.put("genre", genre);
                            map.put("intro", intro);
                            map.put("day", day);
                            map.put("thumbnail", thumbnail);
                            map.put("likes", likes);
                            map.put("isend", isend);
                            map.put("isstop", isstop);
                            map.put("startdate", startdate);

                            //Log.i("[MAIN RESULT]", name + "//" + author);
                            int flag = 0;

                            if (webtoonList.size() > 0) {
                                for (int j = 0; j < webtoonList.size(); j++) {
                                    HashMap<String, String> webtoon2 = webtoonList.get(j);

                                    if (webtoon2.get("wid").equals(wid)) {
                                        Log.d("TAGG", webtoon2.get("wid"));
                                        Log.d("TAGG", wid);

                                        flag = 1;
                                    }
                                }
                            }

                            if (flag == 0)
                                webtoonList.add(map);
                        }

                        new WebtoonList().setWebtoonList(webtoonList);
                    }
                }
                else {
                    Log.i("[MAIN RESULT]", "WEBTOON NOT FOUND!");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
