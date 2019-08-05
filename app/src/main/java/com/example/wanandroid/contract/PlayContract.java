package com.example.wanandroid.contract;

import com.example.wanandroid.base.AbsPresenter;
import com.example.wanandroid.base.BaseView;
import com.example.wanandroid.model.bean.PlayBean;

public class PlayContract {
    public interface View extends BaseView{
        void getNaviOk(PlayBean playBean);

        void getNaviErr();
    }

    public interface Presenter extends AbsPresenter<PlayContract.View>{
        void getNavi();
    }
}
