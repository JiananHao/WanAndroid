package com.example.wanandroid.base;

public interface AbsPresenter<V extends BaseView> {

    void attachView(V view);

    void detachView();
}
