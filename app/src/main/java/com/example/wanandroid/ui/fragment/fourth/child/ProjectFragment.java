package com.example.wanandroid.ui.fragment.fourth.child;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseFragment;
import com.example.wanandroid.contract.ProjectContract;
import com.example.wanandroid.model.bean.ProjectBean;
import com.example.wanandroid.presenter.ProjectPresenter;
import com.example.wanandroid.ui.adapter.ViewPagerFragmentAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

public class ProjectFragment extends BaseFragment<ProjectPresenter> implements ProjectContract.View {

    private String TAG = "ProjectFragment";
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<String> nameList;
    private List<Integer> idList;
    private List<SupportFragment> fragments;
    private ViewPagerFragmentAdapter adapter;

    public static ProjectFragment getInstance(){
        Bundle bundle = new Bundle();
        ProjectFragment projectFragment = new ProjectFragment();
        projectFragment.setArguments(bundle);
        return projectFragment;
    }

    @Override
    protected void initInject() {
        super.initInject();
        fragmentComponent.inject(this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_project;
    }

    @Override
    protected void initUI(View view) {
        tabLayout = view.findViewById(R.id.tab_project);
        viewPager = view.findViewById(R.id.vp_project);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    @Override
    protected void initData() {
        nameList = new ArrayList<>();
        idList = new ArrayList<>();
        fragments = new ArrayList<>();
        mPresenter.getProject();
        adapter = new ViewPagerFragmentAdapter(getChildFragmentManager(),nameList,fragments);
        viewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void getProjectOk(ProjectBean projectBean) {

        for (int i=0;i < projectBean.getData().size();i++){
            nameList.add(projectBean.getData().get(i).getName());
            idList.add(projectBean.getData().get(i).getId());
            fragments.add(ItemFragment.getInstance(projectBean.getData().get(i).getId()));
            Log.d(TAG,"======name======"+ projectBean.getData().get(i).getName());
        }
        adapter.notifyDataSetChanged();
    }
}
