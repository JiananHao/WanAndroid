package com.example.wanandroid.ui.activity;

import com.example.wanandroid.base.BaseAcitivty;
import com.example.wanandroid.R;
import com.example.wanandroid.ui.fragment.first.FirstFragment;
import com.example.wanandroid.ui.fragment.five.FiveFragment;
import com.example.wanandroid.ui.fragment.fourth.FourthFragment;
import com.example.wanandroid.ui.fragment.second.SecondFragment;
import com.example.wanandroid.ui.fragment.third.ThirdFragment;
import com.example.wanandroid.util.BottomNavigationViewHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;

import android.util.Log;
import android.view.MenuItem;

import me.yokeyword.fragmentation.SupportFragment;


public class MainActivity extends BaseAcitivty {
    private BottomNavigationView navView;
    private SupportFragment[] fragments = new SupportFragment[5];
    private int lastIndex;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    switchFragment(0);
                    break;
                case R.id.navigation_knowledge:
                    switchFragment(1);
                    break;
                case R.id.navigation_play:
                    switchFragment(2);
                    break;
                case R.id.navigation_project:
                    switchFragment(3);
                    break;
                case R.id.navigation_mine:
                    switchFragment(4);
                    break;
            }
            return true;
        }
    };

    @Override
    protected int getLayout() {
        Log.d("Hao","=====ggg======");
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        Log.d("Hao","=====ddd======");
        navView = findViewById(R.id.nav_view);
        initFragments();
        switchFragment(0);
    }

    @Override
    protected void initData() {
        BottomNavigationViewHelper.disableShiftMode(navView);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Log.d("Hao","====sss=======");
    }

    private void initFragments(){
        SupportFragment firstFragment = findFragment(FirstFragment.class);
        if (firstFragment == null){
            fragments[0] = FirstFragment.getInstance();
            fragments[1] = SecondFragment.getInstance();
            fragments[2] = ThirdFragment.getInstance();
            fragments[3] = FourthFragment.getInstance();
            fragments[4] = FiveFragment.getInstance();

            loadMultipleRootFragment(R.id.fl_container,0,
                    fragments[0],
                    fragments[1],
                    fragments[2],
                    fragments[3],
                    fragments[4]);
        }else {
            fragments[0] = firstFragment;
            fragments[1] = findFragment(SecondFragment.class);
            fragments[2] = findFragment(ThirdFragment.class);
            fragments[3] = findFragment(FourthFragment.class);
            fragments[4] = findFragment(FiveFragment.class);
        }

    }

    private void switchFragment(int index){
        SupportFragment currentFragment = fragments[index];
        SupportFragment lastFragment = fragments[lastIndex];
        lastIndex = index;
        showHideFragment(currentFragment,lastFragment);
        Log.d("Hao","=======ss====");
    }
}
