package com.example.wanandroid.ui.adapter;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.wanandroid.R;
import com.example.wanandroid.model.bean.KnowledgeBean;

import java.util.List;

import androidx.annotation.Nullable;

public class KnowledgeAdapter extends BaseQuickAdapter<KnowledgeBean.DataBean,KnowledgeViewHolder> {

    public KnowledgeAdapter(int layoutResId, @Nullable List<KnowledgeBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(KnowledgeViewHolder helper, KnowledgeBean.DataBean item) {

        if (!TextUtils.isEmpty(item.getName())){
            helper.setText(R.id.tv_knowledge_title,item.getName());
        }
        if (item.getChildren() != null && item.getChildren().size() > 0){
            helper.setText(R.id.tv_knowledge_content,item.getChildren().get(0).getName());
        }
    }

}
