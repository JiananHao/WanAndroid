package com.example.wanandroid.ui.adapter;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {

    private List<String> titleList;
    private List<SupportFragment> fragmentList;

    public ViewPagerFragmentAdapter(FragmentManager fm, List<String> titles,List<SupportFragment> fragments) {
        super(fm);
        titleList = titles;
        fragmentList = fragments;
    }

    @Override
    public SupportFragment getItem(int position) {
        Log.d("ViewPager","===getItem=="+ fragmentList.get(position));
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        Log.d("ViewPager","===getCount=="+ titleList.size());
        return titleList.size();
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        Log.d("ViewPager","====="+ titleList.get(position));
        return titleList.get(position);
    }
}
