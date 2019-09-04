package com.example.wanandroid.ui.adapter;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.example.wanandroid.R;
import com.example.wanandroid.model.bean.PlayBean;

import java.util.List;

public class NaviRightAdapter extends BaseSectionQuickAdapter<PlayBean.DataBean,NaviRightViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public NaviRightAdapter(int layoutResId, int sectionHeadResId, List<PlayBean.DataBean> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(NaviRightViewHolder helper, PlayBean.DataBean item) {
        Log.d("hao","===convertHead===="+item.header);
        helper.setText(R.id.navi_right_title,item.header);
    }

    @Override
    protected void convert(NaviRightViewHolder helper, PlayBean.DataBean item) {
        final PlayBean.DataBean.ArticlesBean articlesBean = item.t;
        Log.d("hao","===NaviRightAdapter===="+articlesBean.getTitle());
        helper.setText(R.id.navi_right,articlesBean.getTitle());
        helper.addOnClickListener(R.id.navi_right);

    }
}
