package com.example.wanandroid.ui.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.wanandroid.R;
import com.example.wanandroid.model.bean.ProjectItemBean;

import java.util.List;

public class ProjectItemAdapter extends BaseQuickAdapter<ProjectItemBean.DataBean.DatasBean,ProjectViewHolder> {


    public ProjectItemAdapter(int layoutResId, @Nullable List<ProjectItemBean.DataBean.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(ProjectViewHolder helper, ProjectItemBean.DataBean.DatasBean item) {

        if (item != null){
            Glide.with(mContext).asBitmap().load(item.getEnvelopePic()).into((ImageView) helper.getView(R.id.project_iv));
            helper.setText(R.id.project_tv_title,item.getTitle());
            helper.setText(R.id.project_tv_time,item.getNiceDate());
            helper.setText(R.id.project_tv_author,item.getAuthor());
        }
    }
}
