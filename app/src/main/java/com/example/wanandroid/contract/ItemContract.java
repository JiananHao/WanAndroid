package com.example.wanandroid.contract;

import com.example.wanandroid.base.AbsPresenter;
import com.example.wanandroid.base.BaseView;
import com.example.wanandroid.model.bean.ProjectItemBean;

public class ItemContract {
    public interface View extends BaseView{

        void getProjectItemOk(ProjectItemBean projectItemBean);
    }

    public interface Presenter extends AbsPresenter<ItemContract.View>{
        void getProjectItem(int num,int cid);
    }
}
