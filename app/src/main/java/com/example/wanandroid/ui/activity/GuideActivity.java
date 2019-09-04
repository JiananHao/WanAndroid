package com.example.wanandroid.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseAcitivty;

import me.relex.circleindicator.CircleIndicator;

public class GuideActivity extends BaseAcitivty {

    ViewPager viewPager;
    CircleIndicator mIndicator;
    Button mButton;
    int[] resBack = {
            R.mipmap.one,
            R.mipmap.two,
            R.mipmap.three
    };
    @Override
    protected int getLayout() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initView() {
        mButton = findViewById(R.id.bt_go);
        viewPager = findViewById(R.id.guide_vp);
        mIndicator = findViewById(R.id.indicator);
        mButton.setVisibility(View.GONE);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(GuideActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        MyAdapter myAdapter = new MyAdapter(resBack);
        viewPager.setAdapter(myAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == (resBack.length-1)){
                    mButton.setVisibility(View.VISIBLE);
                }else {
                    mButton.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mIndicator.setViewPager(viewPager);
        myAdapter.registerDataSetObserver(mIndicator.getDataSetObserver());
    }
    class MyAdapter extends PagerAdapter{
        int[] back;
        public MyAdapter(int[] res) {
            back = res;
        }

        @Override
        public int getCount() {
            return back.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view==object;
        }
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView imageView = new ImageView(container.getContext());
            imageView.setBackgroundResource(back[position]);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }
}
