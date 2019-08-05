package com.example.wanandroid.ui.fragment.five;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.wanandroid.base.BaseFragment;
import com.example.wanandroid.R;
import com.example.wanandroid.ui.fragment.five.child.MineFragment;

public class FiveFragment extends BaseFragment {

    public static FiveFragment getInstance(){
        Bundle bundle = new Bundle();
        FiveFragment fiveFragment = new FiveFragment();
        fiveFragment.setArguments(bundle);
        return fiveFragment;
    }
    @Override
    public int getLayoutID() {
        return R.layout.fragment_five;
    }

    @Override
    protected void initUI(View view) {

    }

    @Override
    protected void initData() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutID(),container,false);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        if (findChildFragment(MineFragment.class) == null) {
            loadRootFragment(R.id.fl_five_container, MineFragment.getInstance());
        }
    }
}
