package com.example.wanandroid.contract;

import com.example.wanandroid.base.AbsPresenter;
import com.example.wanandroid.base.BaseView;
import com.example.wanandroid.model.bean.BannerBean;
import com.example.wanandroid.model.bean.HomeArticleBean;

import java.util.List;

public class HomeContract {
    public interface View extends BaseView{
        void getHomeListOk(HomeArticleBean homeArticleBean);

        void getHomeListErr();

        void getBannerOk(BannerBean bannerBeans);

        void getBannerErr();
    }

    public interface Presenter extends AbsPresenter<HomeContract.View>{
        void getHomeList(int page);

        void getBanner();

    }
}
