package com.example.wanandroid.presenter;

import android.util.Log;

import com.example.wanandroid.base.BasePresenter;
import com.example.wanandroid.contract.ProjectContract;
import com.example.wanandroid.model.api.ApiService;
import com.example.wanandroid.model.api.ApiStore;
import com.example.wanandroid.model.bean.ProjectBean;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ProjectPresenter extends BasePresenter<ProjectContract.View> implements ProjectContract.Presenter{

    private String TAG = "ProjectPresenter";

    @Inject
    public ProjectPresenter(){

    }

    @Override
    public void getProject() {
        ApiStore.createApi(ApiService.class)
                .getProject()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProjectBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ProjectBean projectBean) {
                        mView.getProjectOk(projectBean);
                        Log.d(TAG,"======getProjectOk======");
                    }
                });
    }
}
