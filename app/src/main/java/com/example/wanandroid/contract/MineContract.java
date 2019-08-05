package com.example.wanandroid.contract;

import com.example.wanandroid.base.AbsPresenter;
import com.example.wanandroid.base.BasePresenter;
import com.example.wanandroid.base.BaseView;

public class MineContract {
    public interface View extends BaseView{

    }

    public interface Presenter extends AbsPresenter<View> {

    }
}
