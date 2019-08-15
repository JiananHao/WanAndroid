package com.example.wanandroid.ui.adapter;

import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.wanandroid.R;

import java.util.ArrayList;
import java.util.List;

public class NaviLeftAdapter extends BaseQuickAdapter<String,NaviLeftViewHolder> {

    private List<TextView> textViews = new ArrayList<>();
    public NaviLeftAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(NaviLeftViewHolder helper, String item) {
        helper.setText(R.id.navi_left,item).addOnClickListener(R.id.item);
        textViews.add((TextView) helper.getView(R.id.navi_left));
        Log.d("hao","===NaviLeftAdapter====convert");
        if (textViews != null && getData() != null && textViews.size() == getData().size()){
            selectItem(0);
        }
        helper.getView(R.id.item).setSelected(true);
    }

    public void selectItem(int postion) {
        for (int i = 0 ;i < getData().size();i++){
            if (postion == i){
                textViews.get(i).setBackgroundColor(0xff0068b7);
            }else {
                textViews.get(i).setBackgroundColor(0xffffffff);
            }
        }
    }
}
