package com.example.wanandroid.ui.fragment.second;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.wanandroid.base.BaseFragment;
import com.example.wanandroid.R;
import com.example.wanandroid.ui.fragment.second.child.KnowledgeFragment;

public class SecondFragment extends BaseFragment {

    public static SecondFragment getInstance(){
        Bundle bundle = new Bundle();
        SecondFragment secondFragment = new SecondFragment();
        secondFragment.setArguments(bundle);
        return secondFragment;
    }
    @Override
    public int getLayoutID() {
        return R.layout.fragment_second;
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
        if (findChildFragment(KnowledgeFragment.class) == null) {
            loadRootFragment(R.id.fl_second_container, KnowledgeFragment.getInstance());
        }
    }
}
