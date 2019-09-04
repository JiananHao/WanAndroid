package com.example.wanandroid.ui.fragment.five.child;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wanandroid.R;

import java.util.List;

public class SearchAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public SearchAdapter(int layoutResId, @Nullable List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_search,item);
    }
}
