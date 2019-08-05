package com.example.wanandroid.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.wanandroid.di.Component.DaggerFragmentComponent;
import com.example.wanandroid.di.Component.FragmentComponent;

import javax.inject.Inject;

import me.yokeyword.fragmentation.SupportFragment;

public abstract class BaseFragment<P extends AbsPresenter> extends SupportFragment implements BaseView {

    protected Activity activity;
    protected MyApplication context;
    protected FragmentComponent fragmentComponent;

    @Inject
    protected P mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutID(),container,false);
        initUI(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activity = getActivity();
        context = MyApplication.getInstance();
        initFragmentComponent();
        initInject();
        createView();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        initData();
    }

    @Override
    public void onDestroy() {
        if (mPresenter != null){
            mPresenter.detachView();
            mPresenter = null;
        }
        super.onDestroy();
    }

    public abstract int getLayoutID();

    private void createView(){
        if (mPresenter != null){
            mPresenter.attachView(this);
        }
    }
    protected abstract void initUI(View view);

    protected abstract void initData();

    private void initFragmentComponent(){
        fragmentComponent = DaggerFragmentComponent.builder()
                .applicationComponent(MyApplication.getInstance().getApplicationComponent())
                .build();
    }

    protected void initInject(){

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
