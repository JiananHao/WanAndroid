package com.example.wanandroid.presenter;

import android.util.Log;

import com.example.wanandroid.base.BasePresenter;
import com.example.wanandroid.contract.KnowledgeContract;
import com.example.wanandroid.model.api.ApiService;
import com.example.wanandroid.model.api.ApiStore;
import com.example.wanandroid.model.bean.KnowledgeBean;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class KnowledgePresenter extends BasePresenter<KnowledgeContract.View> implements KnowledgeContract.Presenter{

    private String Tag = "KnowledgePresenter";
    @Inject
    public KnowledgePresenter(){

    }
    @Override
    public void getTree() {

        ApiStore.createApi(ApiService.class)
                .getTree()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<KnowledgeBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.getTreeErr(e);
                        Log.d(Tag,"======err");
                    }

                    @Override
                    public void onNext(KnowledgeBean knowledgeBean) {
                        mView.getTreeOk(knowledgeBean);
                        Log.d(Tag,"======getTreeOk");
                    }
                });
    }

    @Override
    public void getTreeDetail() {

    }
}
