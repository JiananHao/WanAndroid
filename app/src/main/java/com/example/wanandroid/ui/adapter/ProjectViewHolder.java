package com.example.wanandroid.ui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wanandroid.R;

public class ProjectViewHolder extends BaseViewHolder {

    ImageView imageView;
    TextView tvTitle;
    TextView tvTime;
    TextView tvAuthor;

    public ProjectViewHolder(View view) {
        super(view);
        imageView = view.findViewById(R.id.project_iv);
        tvTitle = view.findViewById(R.id.project_tv_title);
        tvTime = view.findViewById(R.id.project_tv_time);
        tvAuthor = view.findViewById(R.id.project_tv_author);
    }
}
