package com.example.wanandroid.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;

import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseAcitivty;

public class SplashActivity extends BaseAcitivty {

    private boolean isFirst = true;
    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        final SharedPreferences sp = getSharedPreferences("isFirst", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sp.edit();
        isFirst = sp.getBoolean("isFirst",true);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent();
                if (isFirst){
                    editor.putBoolean("isFirst",false);
                    editor.apply();
                    intent.setClass(SplashActivity.this,GuideActivity.class);
                }else {
                    intent.setClass(SplashActivity.this,MainActivity.class);
                }
                startActivity(intent);
                finish();
            }
        },3000);
    }

    @Override
    protected void initData() {

    }
}
