package com.example.wanandroid.base;

import android.app.Application;

import com.example.wanandroid.di.Component.ApplicationComponent;
import com.example.wanandroid.di.Component.DaggerApplicationComponent;
import com.example.wanandroid.di.Module.ApplicationModule;
import com.example.wanandroid.util.webviewUtil.SonicRuntimeImpl;
import com.tencent.sonic.sdk.SonicConfig;
import com.tencent.sonic.sdk.SonicEngine;

public class MyApplication extends Application {

    private static MyApplication myApplication;
    private ApplicationComponent applicationComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        initApplicationComponent();

        if (!SonicEngine.isGetInstanceAllowed()) {
            SonicEngine.createInstance(new SonicRuntimeImpl(myApplication), new SonicConfig.Builder().build());
        }
    }

    public static MyApplication getInstance(){
        return myApplication;
    }

    private void initApplicationComponent(){
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(myApplication))
                .build();
    }

    public ApplicationComponent getApplicationComponent(){
        return applicationComponent;
    }
}
