package com.example.wanandroid.ui.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wanandroid.R;

class PlayViewHolder extends BaseViewHolder {

    TextView tvPlayTitle;
    TextView tvPlayContent;

    public PlayViewHolder(View view) {
        super(view);
        tvPlayTitle = view.findViewById(R.id.tv_play_title);
        tvPlayContent = view.findViewById(R.id.tv_play_content);
    }
}
