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
    @Inject
    public ProjectItemPresenter(){

    }
    @Override
    public void getProjectItem(int num,int cid) {
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

                    }

                    @Override
                    public void onNext(ProjectItemBean projectItemBean) {
                        mView.getProjectItemOk(projectItemBean);
                        Log.d("ProjectItem","========");
                    }
                });
    }
}
