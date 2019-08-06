package com.example.wanandroid.presenter;

import android.util.Log;

import com.example.wanandroid.base.BasePresenter;
import com.example.wanandroid.contract.ItemContract;
import com.example.wanandroid.model.api.ApiService;
import com.example.wanandroid.model.api.ApiStore;
import com.example.wanandroid.model.bean.ProjectItemBean;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ProjectItemPresenter extends BasePresenter<ItemContract.View> implements ItemContract.Presenter {

    private int currentNum = 1;
    private boolean isRefresh = true;
    @Inject
    public ProjectItemPresenter(){

    }
    @Override
    public void getProjectItem(int num,int cid) {
        Log.d("ProjectItem","========"+ num + "===="+ cid);
        ApiStore.createApi(ApiService.class)
                .getProjectItem(num,cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProjectItemBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.getProjectItemErr();
                    }

                    @Override
                    public void onNext(ProjectItemBean projectItemBean) {
                        mView.getProjectItemOk(projectItemBean,isRefresh);
                        Log.d("ProjectItem","========");
                    }
                });
    }

    @Override
    public void getRefreshProjectItem(int cid) {
        isRefresh = true;
        currentNum = 1;
        getProjectItem(currentNum,cid);
    }

    @Override
    public void getMoreProjectItem(int cid) {
        isRefresh = false;
        currentNum++;
        getProjectItem(currentNum,cid);
    }
}
