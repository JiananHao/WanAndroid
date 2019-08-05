package com.example.wanandroid.ui.fragment.first;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.wanandroid.R;
import com.example.wanandroid.ui.fragment.first.child.HomeFragment;

import me.yokeyword.fragmentation.SupportFragment;

public class FirstFragment extends SupportFragment {

    public static FirstFragment getInstance(){
        Bundle bundle = new Bundle();
        FirstFragment firstFragment = new FirstFragment();
        firstFragment.setArguments(bundle);
        return firstFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first,container,false);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        if (findChildFragment(HomeFragment.class) == null) {
            loadRootFragment(R.id.fl_first_container, HomeFragment.getInstance());
            Log.d("Hao","===========");
        }
    }
}
