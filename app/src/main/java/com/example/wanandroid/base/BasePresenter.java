package com.example.wanandroid.base;

public class BasePresenter<V extends BaseView> implements AbsPresenter<V> {

    protected V mView;

    @Override
    public void attachView(V view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        if (mView != null){
            this.mView = null;
        }
    }
}
