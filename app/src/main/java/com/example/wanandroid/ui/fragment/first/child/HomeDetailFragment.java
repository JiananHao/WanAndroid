package com.example.wanandroid.ui.fragment.first.child;

import android.os.Bundle;
import android.view.View;
import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseFragment;
import com.example.wanandroid.model.bean.HomeArticleBean;

public class HomeDetailFragment extends BaseFragment {

    public static HomeDetailFragment getInstance(HomeArticleBean.DataBean.DatasBean datasBean){
        Bundle bundle = new Bundle();
        bundle.putParcelable("bean",datasBean);
        HomeDetailFragment homeDetailFragment = new HomeDetailFragment();
        homeDetailFragment.setArguments(bundle);
        return homeDetailFragment;
    }
    @Override
    public int getLayoutID() {
        return R.layout.fragment_detail;
    }

    @Override
    protected void initUI(View view) {

    }

    @Override
    protected void initData() {

    }
}
