package com.example.wanandroid.ui.adapter;

import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.wanandroid.R;
import com.example.wanandroid.model.bean.PlayBean;

import java.util.List;

public class PlayAdapter extends BaseQuickAdapter<PlayBean.DataBean,PlayViewHolder> {
    public PlayAdapter(int layoutResId, @Nullable List<PlayBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(PlayViewHolder helper, PlayBean.DataBean item) {
        if (!TextUtils.isEmpty(item.getName())){
            helper.setText(R.id.tv_play_title,item.getName());
        }
        if (item.getArticles().size() > 0){
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < item.getArticles().size();i++){
                stringBuilder.append(item.getArticles().get(i).getTitle()+ "    ");
            }
            helper.setText(R.id.tv_play_content,stringBuilder);
        }
    }

}
