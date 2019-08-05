package com.example.wanandroid.di.Component;

import android.content.Context;

import com.example.wanandroid.di.Module.ApplicationModule;

import dagger.Component;

@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Context getApplication();
}
