package com.example.wanandroid.contract;

import com.example.wanandroid.base.AbsPresenter;
import com.example.wanandroid.base.BaseView;
import com.example.wanandroid.model.bean.KnowledgeBean;

public class KnowledgeContract {
    public interface View extends BaseView{
        void getTreeOk(KnowledgeBean knowledgeBean);

        void getTreeErr(Throwable e);

        void getTreeDetailOk();

        void getTreeDetailErr();
    }

    public interface Presenter extends AbsPresenter<KnowledgeContract.View>{
        void getTree();

        void getTreeDetail();
    }
}
