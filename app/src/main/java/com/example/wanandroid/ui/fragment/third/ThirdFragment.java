package com.example.wanandroid.ui.fragment.third;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.wanandroid.base.BaseFragment;
import com.example.wanandroid.R;
import com.example.wanandroid.ui.fragment.third.child.PlayFragment;

public class ThirdFragment extends BaseFragment {

    public static ThirdFragment getInstance(){
        Bundle bundle = new Bundle();
        ThirdFragment thirdFragment = new ThirdFragment();
        thirdFragment.setArguments(bundle);
        return thirdFragment;
    }
    @Override
    public int getLayoutID() {
        return R.layout.fragment_third;
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
        if (findChildFragment(PlayFragment.class) == null) {
            loadRootFragment(R.id.fl_third_container, PlayFragment.getInstance());
        }
    }
}
