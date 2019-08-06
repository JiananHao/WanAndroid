package com.example.wanandroid.ui.fragment.second.child;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseFragment;
import com.example.wanandroid.contract.KnowledgeContract;
import com.example.wanandroid.model.bean.KnowledgeBean;
import com.example.wanandroid.presenter.KnowledgePresenter;
import com.example.wanandroid.ui.adapter.KnowledgeAdapter;
import com.example.wanandroid.util.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

public class KnowledgeFragment extends BaseFragment<KnowledgePresenter> implements KnowledgeContract.View {


    private RecyclerView rvKnowledge;
    private List<KnowledgeBean.DataBean> knowledgeBeanList;
    private KnowledgeAdapter knowledgeAdapter;

    public static KnowledgeFragment getInstance(){
        Bundle bundle = new Bundle();
        KnowledgeFragment knowledgeFragment = new KnowledgeFragment();
        knowledgeFragment.setArguments(bundle);
        return knowledgeFragment;
    }

    @Override
    protected void initInject() {
        super.initInject();
        fragmentComponent.inject(this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_knowledge;
    }

    @Override
    protected void initUI(View view) {
        rvKnowledge = view.findViewById(R.id.rv_knowledge);
        rvKnowledge.setLayoutManager(new GridLayoutManager(context,2));
        RecyclerView.ItemDecoration itemDecoration = new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
                if (itemPosition % 2 == 1) {
                    outRect.left = 10;
                    outRect.right = 20;
                } else {
                    outRect.left = 20;
                    outRect.right = 10;
                }
                if (itemPosition == 0 || itemPosition == 1) {
                    outRect.top = 30;
                }
                outRect.bottom = 30;
            }
        };
        rvKnowledge.addItemDecoration(itemDecoration);
    }

    @Override
    protected void initData() {
        LoadingDialog.getInstance(getContext()).show();
        knowledgeBeanList = new ArrayList<>();
        mPresenter.getTree();
        knowledgeAdapter = new KnowledgeAdapter(R.layout.rv_knowledge_item,knowledgeBeanList);
        rvKnowledge.setAdapter(knowledgeAdapter);
    }

    @Override
    public void getTreeOk(KnowledgeBean knowledgeBean) {
        knowledgeBeanList = knowledgeBean.getData();
        knowledgeAdapter.addData(knowledgeBeanList);
        LoadingDialog.getInstance(getContext()).dismiss();
    }

    @Override
    public void getTreeErr(Throwable throwable) {
        LoadingDialog.getInstance(getContext()).dismiss();
    }

    @Override
    public void getTreeDetailOk() {

    }

    @Override
    public void getTreeDetailErr() {

    }
}
