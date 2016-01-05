package com.skku.ntoon.ntoon;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.skku.ntoon.ntoon.com.skku.ntoon.NtoonService;
import com.skku.ntoon.ntoon.finishtoon.FinishFragment;
import com.skku.ntoon.ntoon.mypage.MypageFragment;
import com.skku.ntoon.ntoon.store.StoreFragment;
import com.skku.ntoon.ntoon.webtoon.WebtoonFragment;


public class MainActivity extends AppCompatActivity {

    Button mypageBtn;
    Button webtoonBtn;
    Button finishBtn;
    Button storeBtn;

    TextView NtoonTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mypageBtn = (Button)findViewById(R.id.mypageBtn_main);
        webtoonBtn = (Button)findViewById(R.id.webtoonBtn_main);
        finishBtn = (Button)findViewById(R.id.finishBtn_main);
        storeBtn = (Button)findViewById(R.id.storeBtn_main);

        NtoonTv = (TextView)findViewById(R.id.NtoonsayTv_main);

        mypageBtn.setOnClickListener(tabListener);
        webtoonBtn.setOnClickListener(tabListener);
        finishBtn.setOnClickListener(tabListener);
        storeBtn.setOnClickListener(tabListener);

        Intent Service = new Intent(this, NtoonService.class);
        startService(Service);

    }

    View.OnClickListener tabListener = new View.OnClickListener() {

        Fragment fr;

        @Override
        public void onClick(View v) {

            if(v == findViewById(R.id.mypageBtn_main)){
                fr = new MypageFragment();
            }else if(v == findViewById(R.id.webtoonBtn_main)){
                fr = new WebtoonFragment();
            }else if(v == findViewById(R.id.finishBtn_main)){
                fr = new FinishFragment();
            }else if(v == findViewById(R.id.storeBtn_main)){
                fr = new StoreFragment();
            }

            FragmentManager fm = getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.container, fr);
            fragmentTransaction.commit();
        }
    };
}
