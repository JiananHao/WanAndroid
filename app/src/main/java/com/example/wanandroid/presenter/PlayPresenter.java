package com.example.wanandroid.presenter;

import com.example.wanandroid.base.BasePresenter;
import com.example.wanandroid.contract.PlayContract;
import com.example.wanandroid.model.api.ApiService;
import com.example.wanandroid.model.api.ApiStore;
import com.example.wanandroid.model.bean.PlayBean;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PlayPresenter extends BasePresenter<PlayContract.View> implements PlayContract.Presenter{


    @Inject
    public PlayPresenter(){

    }

    @Override
    public void getNavi() {
        ApiStore.createApi(ApiService.class)
                .getNavi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PlayBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(PlayBean playBean) {
                        mView.getNaviOk(playBean);
                    }
                });
    }
}
