package com.example.wanandroid.contract;

import com.example.wanandroid.base.AbsPresenter;
import com.example.wanandroid.base.BaseView;
import com.example.wanandroid.model.bean.UserBean;

public class LoginContract {

    public interface View extends BaseView{

        void loginOk(UserBean userBean);

    }

    public interface Presenter extends AbsPresenter<LoginContract.View>{

        void login(String username,String password);
    }
}
