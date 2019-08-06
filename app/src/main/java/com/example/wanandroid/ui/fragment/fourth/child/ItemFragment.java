package com.example.wanandroid.ui.fragment.fourth.child;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseFragment;
import com.example.wanandroid.contract.ItemContract;
import com.example.wanandroid.model.bean.ProjectItemBean;
import com.example.wanandroid.presenter.ProjectItemPresenter;
import com.example.wanandroid.ui.adapter.ProjectItemAdapter;
import com.example.wanandroid.util.LoadingDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.footer.FalsifyFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.header.FalsifyHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class ItemFragment extends BaseFragment<ProjectItemPresenter> implements ItemContract.View, OnRefreshListener, OnLoadMoreListener {

    private int cid;
    private SmartRefreshLayout refreshLayout;
    private RecyclerView rvProject;
    private List<ProjectItemBean.DataBean.DatasBean> dataBeans;
    private ProjectItemAdapter projectItemAdapter;

    public static ItemFragment getInstance(int i){
        Bundle bundle = new Bundle();
        ItemFragment itemFragment = new ItemFragment();
        bundle.putInt("id",i);
        itemFragment.setArguments(bundle);
        return itemFragment;
    }

    @Override
    protected void initInject() {
        super.initInject();
        fragmentComponent.inject(this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_project_item;
    }

    @Override
    protected void initUI(View view) {
        refreshLayout = view.findViewById(R.id.project_refresh);
        refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()).setEnableLastTime(true));
        refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()).setArrowDrawable(null).setFinishDuration(0));
        refreshLayout.setEnableAutoLoadMore(false);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadMoreListener(this);

        rvProject = view.findViewById(R.id.rv_project);
        rvProject.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
    }

    @Override
    protected void initData() {
        LoadingDialog.getInstance(getContext()).show();
        dataBeans = new ArrayList<>();
        cid = getArguments().getInt("id");
        Log.d("hao","=======cid========="+ cid);
        mPresenter.getProjectItem(1,cid);
        projectItemAdapter = new ProjectItemAdapter(R.layout.project_item_item,dataBeans);
        rvProject.setAdapter(projectItemAdapter);

    }

    @Override
    public void getProjectItemOk(ProjectItemBean projectItemBean,boolean isRefresh) {
        if (projectItemAdapter == null){
            return;
        }
        if (isRefresh){
            dataBeans = projectItemBean.getData().getDatas();
            projectItemAdapter.replaceData(projectItemBean.getData().getDatas());
        }else {
            dataBeans.addAll(projectItemBean.getData().getDatas());
            projectItemAdapter.addData(projectItemBean.getData().getDatas());
        }
        LoadingDialog.getInstance(getContext()).dismiss();
    }

    @Override
    public void getProjectItemErr() {
        LoadingDialog.getInstance(getContext()).dismiss();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        Log.d("hao","===more==cid====="+ cid);
        refreshLayout.finishLoadMore(1000);
        mPresenter.getMoreProjectItem(cid);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        refreshLayout.finishRefresh(1000);
        mPresenter.getRefreshProjectItem(cid);
    }
}
