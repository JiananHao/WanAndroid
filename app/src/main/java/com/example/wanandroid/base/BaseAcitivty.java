package com.example.wanandroid.base;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import com.example.wanandroid.di.Component.ActivityComponent;
import com.example.wanandroid.di.Component.DaggerActivityComponent;
import com.example.wanandroid.di.Module.ActivityModule;

import javax.inject.Inject;

import me.yokeyword.fragmentation.SupportActivity;

public abstract class BaseAcitivty<P extends AbsPresenter> extends SupportActivity implements BaseView{

    protected ActivityComponent activityComponent;
    @Inject
    protected P mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Hao","=====base======");
        setContentView(getLayout());
        initActivityComponent();
        initInject();
        initBind();
        onViewCreated();
        initToolbar();
        initView();
        initData();
        Log.d("Hao","=====base======");
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null){
            mPresenter.detachView();
            mPresenter = null;
        }
        super.onDestroy();
    }

    protected abstract int getLayout();

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 初始化data
     */
    protected abstract void initData();

    public void initBind() {
//        ButterKnife.bind(this);
    }
    /**
     * 初始化toolbar
     */
    protected void initToolbar(){

    }

    /**
     * 初始化dagger
     */
    protected void initInject(){

    }

    /**
     * 初始化ActivityComponent
     */
    private void initActivityComponent(){
        activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(MyApplication.getInstance().getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }

    /**
     * 绑定view
     */
    protected void onViewCreated(){
        if (mPresenter != null){
            mPresenter.attachView(this);
        }
    }

    @Override
    public void loading() {

    }

    @Override
    public void loaded() {

    }

    @Override
    public void loadErr() {

    }
}
