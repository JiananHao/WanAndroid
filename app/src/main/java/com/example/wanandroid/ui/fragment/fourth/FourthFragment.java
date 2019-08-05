package com.example.wanandroid.ui.fragment.fourth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.wanandroid.base.BaseFragment;
import com.example.wanandroid.R;
import com.example.wanandroid.ui.fragment.fourth.child.ProjectFragment;

public class FourthFragment extends BaseFragment {

    public static FourthFragment getInstance(){
        Bundle bundle = new Bundle();
        FourthFragment fourthFragment = new FourthFragment();
        fourthFragment.setArguments(bundle);
        return fourthFragment;
    }
    @Override
    public int getLayoutID() {
        return R.layout.fragment_fourth;
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
        if (findChildFragment(ProjectFragment.class) == null) {
            loadRootFragment(R.id.fl_fourth_container, ProjectFragment.getInstance());
        }
    }
}
