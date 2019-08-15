package com.example.wanandroid.ui.fragment.third.child;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseFragment;
import com.example.wanandroid.contract.PlayContract;
import com.example.wanandroid.model.bean.PlayBean;
import com.example.wanandroid.presenter.PlayPresenter;
import com.example.wanandroid.ui.adapter.PlayAdapter;
import com.example.wanandroid.util.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

public class PlayFragment extends BaseFragment<PlayPresenter> implements PlayContract.View {

    private RecyclerView rvPlay;
    private PlayAdapter playAdapter;
    private List<PlayBean.DataBean> playBeans;

    public static PlayFragment getInstance(){
        Bundle bundle = new Bundle();
        PlayFragment playFragment = new PlayFragment();
        playFragment.setArguments(bundle);
        return playFragment;
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_play;
    }

    @Override
    protected void initUI(View view) {
        rvPlay = view.findViewById(R.id.rv_play);
        rvPlay.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    protected void initInject() {
        super.initInject();
        fragmentComponent.inject(this);
    }

    @Override
    protected void initData() {
        LoadingDialog.getInstance(getContext()).show();
        playBeans = new ArrayList<>();
        mPresenter.getNavi();
        playAdapter = new PlayAdapter(R.layout.rv_play_item,playBeans);
        rvPlay.setAdapter(playAdapter);
    }

    @Override
    public void getNaviOk(List<PlayBean.DataBean> playBean) {
        playAdapter.addData(playBean);
        LoadingDialog.getInstance(getContext()).dismiss();
    }

    @Override
    public void getNaviErr() {
        LoadingDialog.getInstance(getContext()).dismiss();
    }
}
