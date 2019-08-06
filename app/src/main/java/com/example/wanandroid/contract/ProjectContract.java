package com.example.wanandroid.contract;

import com.example.wanandroid.base.AbsPresenter;
import com.example.wanandroid.base.BaseView;
import com.example.wanandroid.model.bean.ProjectBean;

public class ProjectContract {

    public interface View extends BaseView{
        void getProjectOk(ProjectBean projectBean);

        void getProjectErr();
    }

    public interface Presenter extends AbsPresenter<ProjectContract.View>{
        void getProject();
    }
}
