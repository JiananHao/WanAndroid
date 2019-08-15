package com.example.wanandroid.contract;

import com.example.wanandroid.base.AbsPresenter;
import com.example.wanandroid.base.BaseView;
import com.example.wanandroid.model.bean.PlayBean;

import java.util.List;

public class PlayContract {
    public interface View extends BaseView{
        void getNaviOk(List<PlayBean.DataBean> dataBeans);

        void getNaviErr();
    }

    public interface Presenter extends AbsPresenter<PlayContract.View>{
        void getNavi();
    }
}
