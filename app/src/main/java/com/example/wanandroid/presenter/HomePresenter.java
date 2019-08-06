package com.example.wanandroid.presenter;

import android.util.Log;

import com.example.wanandroid.base.BasePresenter;
import com.example.wanandroid.contract.HomeContract;
import com.example.wanandroid.model.bean.BannerBean;
import com.example.wanandroid.model.bean.HomeArticleBean;
import com.example.wanandroid.model.api.ApiService;
import com.example.wanandroid.model.api.ApiStore;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    private boolean isRefresh = true;
    private int currentPage = 0;

    @Inject
    public HomePresenter(){

    }
    @Override
    public void getHomeList(int page) {
        ApiStore.createApi(ApiService.class)
                .getHomelist(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeArticleBean>() {
                    @Override
                    public void onCompleted() {
                        Log.d("hao","----onCompleted------");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("hao","----onError------");
                        mView.getHomeListErr();
                    }

                    @Override
                    public void onNext(HomeArticleBean homeArticleBean) {
                        mView.getHomeListOk(homeArticleBean,isRefresh);
                        Log.d("hao","----nexthome------");
                    }
                });
    }

    @Override
    public void getFreshHomeList() {
        isRefresh = true;
        currentPage = 0;
        getHomeList(currentPage);
    }

    @Override
    public void getMoreHomeList() {
        isRefresh = false;
        currentPage++;
        getHomeList(currentPage);
    }

    @Override
    public void getBanner() {

        ApiStore.createApi(ApiService.class)
                .getBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BannerBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("hao","----banereee------");
                        Log.d("hao",e.getMessage());
                    }

                    @Override
                    public void onNext(BannerBean bannerBean) {
                        mView.getBannerOk(bannerBean);
                        Log.d("hao","----nextbaner------");
                    }

                });
    }
}
