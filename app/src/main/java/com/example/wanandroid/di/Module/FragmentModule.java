package com.example.wanandroid.di.Module;

import android.app.Activity;

import androidx.fragment.app.Fragment;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {

    private Fragment mfragment;

    public FragmentModule(Fragment fragment) {
        mfragment = fragment;
    }

    @Provides
    Fragment provideFragment(){
        return mfragment;
    }

    @Provides
    Activity provideActivity(){
        return mfragment.getActivity();
    }
}
