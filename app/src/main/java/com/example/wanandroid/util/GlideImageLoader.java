package com.example.wanandroid.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.wanandroid.base.MyApplication;
import com.youth.banner.loader.ImageLoader;

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(MyApplication.getInstance()).load(path).into(imageView);
    }
}
