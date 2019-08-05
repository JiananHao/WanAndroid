package com.example.wanandroid.di.Module;

import android.content.Context;

import com.example.wanandroid.base.MyApplication;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private MyApplication myApplication;

    public ApplicationModule(MyApplication application) {
        myApplication = application;
    }

    @Provides
    Context provideApplication(){
        return myApplication.getApplicationContext();
    }
}
