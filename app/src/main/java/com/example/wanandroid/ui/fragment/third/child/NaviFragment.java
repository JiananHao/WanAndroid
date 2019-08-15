package com.example.wanandroid.ui.fragment.third.child;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseFragment;
import com.example.wanandroid.contract.PlayContract;
import com.example.wanandroid.model.bean.PlayBean;
import com.example.wanandroid.presenter.PlayPresenter;
import com.example.wanandroid.ui.adapter.NaviLeftAdapter;
import com.example.wanandroid.ui.adapter.NaviRightAdapter;

import java.util.ArrayList;
import java.util.List;

public class NaviFragment extends BaseFragment<PlayPresenter> implements PlayContract.View {

    private RecyclerView recyclerLeft;
    private RecyclerView recyclerRight;
    private TextView title;
    private NaviLeftAdapter naviLeftAdapter;
    private NaviRightAdapter naviRightAdapter;
    private GridLayoutManager rightLayoutManager;
    private LinearLayoutManager leftLayoutManager;
    private List<PlayBean.DataBean> rightList;
    private List<String> leftList;
    private List<Integer> toRightPosition = new ArrayList<>();

    public static NaviFragment getInstance(){
        Bundle bundle = new Bundle();
        NaviFragment naviFragment = new NaviFragment();
        naviFragment.setArguments(bundle);
        return naviFragment;
    }
    @Override
    public int getLayoutID() {
        return R.layout.fragment_shop;
    }

    @Override
    protected void initInject() {
        super.initInject();
        fragmentComponent.inject(this);
    }

    @Override
    protected void initUI(View view) {
        recyclerLeft = view.findViewById(R.id.rec_left);
        recyclerRight = view.findViewById(R.id.rec_right);
        title = view.findViewById(R.id.right_title);

    }

    @Override
    protected void initData() {
        rightList = new ArrayList<>();
        leftList = new ArrayList<>();

        mPresenter.getNavi();
        initRightAdapter();
        initLeftAdapter();
    }
    private void initRightAdapter(){
        Log.d("hao","===initRightAdapter====start");
        naviRightAdapter = new NaviRightAdapter(R.layout.navi_right,R.layout.navi_right_title,rightList);
        rightLayoutManager = new GridLayoutManager(context,3);
        recyclerRight.setLayoutManager(rightLayoutManager);
        recyclerRight.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(10
                        ,10
                        ,10
                        ,0);
            }
        });

        recyclerRight.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof GridLayoutManager){
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager)layoutManager;
                    int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                    int indexOf = toRightPosition.indexOf(firstVisibleItemPosition);
                    if (indexOf != -1){
                        naviLeftAdapter.selectItem(indexOf);
                    }
//                    TextView viewByPosition = (TextView) linearLayoutManager.findViewByPosition(firstVisibleItemPosition);
                    Log.d("hao","======firstVisibleItemPosition=="+firstVisibleItemPosition);
                    Log.d("hao","======indexOf=="+indexOf);
                }
            }
        });
        naviRightAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Log.d("hao","===setOnItemChildClickListener===1==");
                switch (view.getId()){
                    case R.id.navi_right:
                        Log.d("hao","===setOnItemChildClickListener=====");
                        String link = ((PlayBean.DataBean) adapter.getData().get(position)).t.getLink();
                        NaviDetailFragment naviDetailFragment = NaviDetailFragment.getInstance(link);
                        start(naviDetailFragment);
                        break;
                }
//                start();

            }
        });
        recyclerRight.setAdapter(naviRightAdapter);
        naviRightAdapter.setNewData(rightList);
        Log.d("hao","===initRightAdapter====end");
    }

    private void initLeftAdapter(){
        Log.d("hao","===initLeftAdapter====start");
        naviLeftAdapter = new NaviLeftAdapter(R.layout.navi_left,leftList);
        leftLayoutManager = new LinearLayoutManager(context);
        recyclerLeft.setLayoutManager(leftLayoutManager);
        recyclerLeft.setAdapter(naviLeftAdapter);
        naviLeftAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.item:
                        naviLeftAdapter.selectItem(position);
                        rightLayoutManager.scrollToPositionWithOffset(toRightPosition.get(position),0);
                    break;
                }
            }
        });
        Log.d("hao","===initLeftAdapter====end");
    }
    @Override
    public void getNaviOk(List<PlayBean.DataBean> dataBeans) {

        for (int i=0; i< dataBeans.size();i++){
            leftList.add(dataBeans.get(i).getName());
            Log.d("hao","===name===="+ dataBeans.get(i).getName());
//            rightList.add(dataBean.ge().get(i));
            rightList.add(new PlayBean.DataBean(true,dataBeans.get(i).getName()));
            for (int j=0;j<dataBeans.get(i).getArticles().size();j++){
                rightList.add(new PlayBean.DataBean(dataBeans.get(i).getArticles().get(j)));
            }
        }
        initRightToPosition();
        Log.d("hao","===rightList===="+ rightList.size());
        naviRightAdapter.notifyDataSetChanged();
        naviLeftAdapter.notifyDataSetChanged();
    }

    private void initRightToPosition(){
        if (rightList.size() > 0){
            for (int i=0;i < rightList.size();i++){
                if (rightList.get(i).isHeader){
                    toRightPosition.add(i);
                    Log.d("hao","===headerindex==="+ i);
                    Log.d("hao","===right==="+ rightList.get(i).header);
                }
            }
        }
    }
    private int leftToPosition(int index){
        int position = 0;
        if (leftList.size() == 0 || rightList.size() == 0){
            return 0;
        }
        for (int i = 0;i< leftList.size();i++){
            if (rightList.get(index).header.equalsIgnoreCase(leftList.get(i))){
                position = i;
            }
        }
        return position;
    }

    @Override
    public void getNaviErr() {

    }
}
