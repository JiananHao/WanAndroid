package com.example.wanandroid.ui.adapter;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.wanandroid.model.bean.HomeArticleBean;
import com.example.wanandroid.R;

import java.util.List;

public class HomeAdapter extends BaseQuickAdapter<HomeArticleBean.DataBean.DatasBean,HomeViewHolder> {
    public HomeAdapter(int layoutResId, @Nullable List<HomeArticleBean.DataBean.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(HomeViewHolder helper, HomeArticleBean.DataBean.DatasBean item) {
        Log.d("hao","----------");
        helper.getView(R.id.tv_tag).setVisibility(View.GONE);
        if (!TextUtils.isEmpty(item.getTitle())) {
            helper.setText(R.id.tv_content, item.getTitle());
        }
        if (!TextUtils.isEmpty(item.getAuthor())) {
            helper.setText(R.id.tv_author, item.getAuthor());
        }
        if (!TextUtils.isEmpty(item.getNiceDate())) {
            helper.setText(R.id.tv_time, item.getNiceDate());
        }
        if (!TextUtils.isEmpty(item.getChapterName())) {
            String classifyName = item.getSuperChapterName() + " / " + item.getChapterName();
            helper.setText(R.id.tv_type, classifyName);
        }
        if (item.getSuperChapterName().contains("项目")) {
            helper.getView(R.id.tv_tag).setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_tag, "项目");
//            helper.setTextColor(R.id.tv_tag,mContext.getResources().getColor(R.color.green));
//            helper.setBackgroundRes(R.id.tv_tag,R.drawable.drawable_shape_green);
        } else if (item.getSuperChapterName().contains("热门")) {
            helper.getView(R.id.tv_tag).setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_tag,"热门");
//            helper.setTextColor(R.id.tv_tag,mContext.getResources().getColor(R.color.red));
//            helper.setBackgroundRes(R.id.tv_tag,R.drawable.drawable_shape_red);
        }
        helper.addOnClickListener(R.id.tv_type);
        helper.addOnClickListener(R.id.image_collect);
        helper.setImageResource(R.id.image_collect, item.isCollect() ? R.drawable.ic_star_black_24dp : R.drawable.ic_star_border_black_24dp);
    }
}
