package com.example.wanandroid.ui.fragment.first.child;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.wanandroid.base.BaseFragment;
import com.example.wanandroid.contract.HomeContract;
import com.example.wanandroid.model.bean.BannerBean;
import com.example.wanandroid.model.bean.HomeArticleBean;
import com.example.wanandroid.presenter.HomePresenter;
import com.example.wanandroid.R;
import com.example.wanandroid.ui.adapter.HomeAdapter;
import com.example.wanandroid.util.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View,HomeAdapter.OnItemClickListener,HomeAdapter.OnItemLongClickListener {

    private RecyclerView recyclerView;
    private LinearLayout bannerView;
    private Banner banner;
    private List<HomeArticleBean.DataBean.DatasBean> mHomeArticleBeans;
    private List<String> linkList;
    private List<String> imageList;
    private List<String> titleList;
    private HomeAdapter homeAdapter;

    public static HomeFragment getInstance(){
        Bundle bundle = new Bundle();
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(bundle);
        return homeFragment;
    }
    @Override
    public int getLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initInject() {
        fragmentComponent.inject(this);
    }

    @Override
    protected void initUI(View view) {
        recyclerView = view.findViewById(R.id.rv_home);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        bannerView = (LinearLayout) getLayoutInflater().inflate(R.layout.view_banner,null);
        banner = bannerView.findViewById(R.id.banner);
        bannerView.removeView(banner);
        bannerView.addView(banner);


    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("hao","----start------");
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }

    @Override
    protected void initData() {
        mHomeArticleBeans = new ArrayList<>();
        linkList = new ArrayList<>();
        imageList = new ArrayList<>();
        titleList = new ArrayList<>();
        mPresenter.getHomeList(0);
        Log.d("hao","----da------");
        mPresenter.getBanner();
        homeAdapter = new HomeAdapter(R.layout.rv_home_item,mHomeArticleBeans);
        homeAdapter.addHeaderView(bannerView);
        homeAdapter.setOnItemClickListener(this);
        homeAdapter.setOnItemLongClickListener(this);
        recyclerView.setAdapter(homeAdapter);
    }

    @Override
    public void loading() {
        super.loading();
    }

    @Override
    public void loaded() {
        super.loaded();
    }

    @Override
    public void loadErr() {
        super.loadErr();
    }

    @Override
    public void getHomeListOk(HomeArticleBean homeArticleBean) {
//        mHomeArticleBeans.addAll(homeArticleBean.getData().getDatas());
        homeAdapter.addData(homeArticleBean.getData().getDatas());
        Toast.makeText(context,"=======",Toast.LENGTH_LONG).show();
    }

    @Override
    public void getHomeListErr() {

    }

    @Override
    public void getBannerOk(BannerBean bannerBean) {
        for ( BannerBean.DataBean banner : bannerBean.getData()) {
            linkList.add(banner.getUrl());
            imageList.add(banner.getImagePath());
            titleList.add(banner.getTitle());
        }
        Log.d("hao","----baner------");
        banner.setImageLoader(new GlideImageLoader())
                .setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
                .setImages(imageList)
                .setBannerAnimation(Transformer.DepthPage)
                .setBannerTitles(titleList)
                .isAutoPlay(true)
                .setDelayTime(5000)
                .setIndicatorGravity(BannerConfig.RIGHT)
                .start();

    }

    @Override
    public void getBannerErr() {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
        return false;
    }
}
