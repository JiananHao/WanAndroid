package com.example.wanandroid.ui.fragment.third.child;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseFragment;
import com.example.wanandroid.util.webviewUtil.DisplayWebView;

public class NaviDetailFragment extends BaseFragment {

    private WebView webView;
    public static NaviDetailFragment getInstance(String link){
        Bundle bundle = new Bundle();
        bundle.putString("link",link);
        NaviDetailFragment naviDetailFragment = new NaviDetailFragment();
        naviDetailFragment.setArguments(bundle);
        return naviDetailFragment;
    }
    @Override
    public int getLayoutID() {
        return R.layout.fragment_navi_detail;
    }

    @Override
    protected void initUI(View view) {
        webView = view.findViewById(R.id.web_navi_detail);
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        String url = bundle.getString("link");
        DisplayWebView.display(url,webView);
    }

    @Override
    public boolean onBackPressedSupport() {
        pop();
        return true;
    }

    @Override
    public void onDestroy() {
        DisplayWebView.closeWebView();
        super.onDestroy();
    }
}
