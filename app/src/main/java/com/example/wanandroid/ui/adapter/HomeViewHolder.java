package com.example.wanandroid.ui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wanandroid.R;

class HomeViewHolder extends BaseViewHolder {

    TextView tvAuthor ;
    TextView tvTime;
    TextView tvType;
    TextView tvContent;
    TextView tvTag;
    ImageView ivCollect;

    public HomeViewHolder(View view) {
        super(view);
        tvAuthor = view.findViewById(R.id.tv_author);
        tvTime = view.findViewById(R.id.tv_time);
        tvType = view.findViewById(R.id.tv_type);
        tvContent = view.findViewById(R.id.tv_content);
        tvTag = view.findViewById(R.id.tv_tag);
        ivCollect = view.findViewById(R.id.image_collect);
    }
}
