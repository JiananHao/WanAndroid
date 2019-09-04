package com.example.wanandroid.ui.fragment.first.child;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
import com.example.wanandroid.util.LoadingDialog;
import com.scwang.smartrefresh.header.BezierCircleHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View,HomeAdapter.OnItemClickListener,HomeAdapter.OnItemLongClickListener, OnRefreshListener, OnLoadMoreListener {

    private SmartRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private LinearLayout bannerView;
    private Banner banner;
    private List<HomeArticleBean.DataBean.DatasBean> mHomeArticleBeans;
    private List<String> linkList;
    private List<String> imageList;
    private List<String> titleList;
    private List<String> bannerLink;
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

        refreshLayout = view.findViewById(R.id.refresh);
        refreshLayout.setEnableAutoLoadMore(false);
        refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()).setEnableLastTime(true));
        refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()).setArrowDrawable(null));
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadMoreListener(this);
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
        LoadingDialog.getInstance(getContext()).show();
        mHomeArticleBeans = new ArrayList<>();
        linkList = new ArrayList<>();
        imageList = new ArrayList<>();
        titleList = new ArrayList<>();
        bannerLink = new ArrayList<>();
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
    public void getHomeListOk(HomeArticleBean homeArticleBean,boolean isRefresh) {
//        mHomeArticleBeans.addAll(homeArticleBean.getData().getDatas());
        if (isRefresh){
            mHomeArticleBeans = homeArticleBean.getData().getDatas();
            homeAdapter.replaceData(homeArticleBean.getData().getDatas());
        }else {
            mHomeArticleBeans.addAll(homeArticleBean.getData().getDatas());
            homeAdapter.addData(homeArticleBean.getData().getDatas());
        }
        LoadingDialog.getInstance(getContext()).dismiss();
    }

    @Override
    public void getHomeListErr() {
        LoadingDialog.getInstance(getContext()).dismiss();
    }

    @Override
    public void getBannerOk(BannerBean bannerBean) {
        for ( BannerBean.DataBean banner : bannerBean.getData()) {
            linkList.add(banner.getUrl());
            imageList.add(banner.getImagePath());
            titleList.add(banner.getTitle());
            bannerLink.add(banner.getUrl());
        }
        Log.d("hao","----baner------");
        banner.setImageLoader(new GlideImageLoader())
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .setImages(imageList)
                .setBannerAnimation(Transformer.DepthPage)
                .setBannerTitles(titleList)
                .isAutoPlay(true)
                .setDelayTime(5000)
                .setIndicatorGravity(BannerConfig.CENTER)
                .start();

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if (bannerLink.size() > 0){
                    BannerFragment bannerFragment = BannerFragment.getInstance(bannerLink.get(position));
                    start(bannerFragment);
                }
            }
        });
    }

    @Override
    public void getBannerErr() {
        LoadingDialog.getInstance(getContext()).dismiss();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        HomeDetailFragment homeDetailFragment = HomeDetailFragment.getInstance((HomeArticleBean.DataBean.DatasBean) adapter.getData().get(position));
        start(homeDetailFragment);
    }

    @Override
    public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
        return false;
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        mPresenter.getMoreHomeList();
        refreshLayout.finishLoadMore(1000);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mPresenter.getFreshHomeList();
        refreshLayout.finishRefresh(1000);
    }
}
