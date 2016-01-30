package com.skku.ntoon.ntoon.Util;

import android.app.Dialog;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.skku.ntoon.ntoon.R;

/**
 * Created by Jonghyeok on 2016. 1. 22..
 */
public class CustomDialog extends Dialog {

    ImageLoader imageLoader = ImageLoader.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);
*/
        setContentView(R.layout.custom_dialog);

        setLayout();
        setTitle(mTitle);
        setmThumbnail(mThumbnail);
        setIntro(mIntro);

        LinearLayout l = (LinearLayout)findViewById(R.id.mainLayout_custom_dialog);
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setVisibility(View.GONE);
            }
        });

    }

    public CustomDialog(Context context) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);

    }

    public CustomDialog(Context context, String title, String intro, String thumbnail)
    {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.mTitle= title;
        this.mIntro = intro;
        this.mThumbnail = thumbnail;

    }

    public void setTitle(String title){
        titleText.setText(title);
    }

    public void setIntro(String intro){
        introText.setText(intro);
    }

    public void setmThumbnail(String thumbnail){

        imageLoader.displayImage(thumbnail, thumbImage);
    }

    // layout
    private String mTitle;
    private String mIntro;
    private String mThumbnail;

    private ImageView thumbImage;
    private TextView titleText;
    private TextView introText;

    private void setLayout(){
        thumbImage = (ImageView)findViewById(R.id.imageView);
        titleText = (TextView)findViewById(R.id.dialog_name_tv);
        introText = (TextView)findViewById(R.id.dialog_intro_tv);
    }
}
