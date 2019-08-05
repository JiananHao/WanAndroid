package com.example.wanandroid.ui.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wanandroid.R;

class KnowledgeViewHolder extends BaseViewHolder {


    TextView tvTitle;
    TextView tvContent;
    public KnowledgeViewHolder(View view) {
        super(view);
        tvTitle = view.findViewById(R.id.tv_knowledge_title);
        tvContent = view.findViewById(R.id.tv_knowledge_content);
    }
}
