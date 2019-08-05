package com.example.wanandroid.di.Component;

import android.app.Activity;

import com.example.wanandroid.di.Module.ActivityModule;

import dagger.Component;

@Component(dependencies = ApplicationComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();


}
