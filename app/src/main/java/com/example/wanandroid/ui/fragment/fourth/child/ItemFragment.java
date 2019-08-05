package com.example.wanandroid.ui.fragment.fourth.child;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseFragment;
import com.example.wanandroid.contract.ItemContract;
import com.example.wanandroid.model.bean.ProjectItemBean;
import com.example.wanandroid.presenter.ProjectItemPresenter;
import com.example.wanandroid.ui.adapter.ProjectItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class ItemFragment extends BaseFragment<ProjectItemPresenter> implements ItemContract.View {

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
        rvProject = view.findViewById(R.id.rv_project);
        rvProject.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
    }

    @Override
    protected void initData() {
        dataBeans = new ArrayList<>();

        int cid = getArguments().getInt("id");
        Log.d("hao","=======cid========="+ cid);
        mPresenter.getProjectItem(1,cid);
        projectItemAdapter = new ProjectItemAdapter(R.layout.project_item_item,dataBeans);
        rvProject.setAdapter(projectItemAdapter);
    }

    @Override
    public void getProjectItemOk(ProjectItemBean projectItemBean) {
        projectItemAdapter.addData(projectItemBean.getData().getDatas());
    }
}
