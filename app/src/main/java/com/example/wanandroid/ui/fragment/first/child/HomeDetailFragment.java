package com.example.wanandroid.ui.fragment.first.child;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseFragment;
import com.example.wanandroid.model.bean.HomeArticleBean;
import com.example.wanandroid.util.webviewUtil.SonicSessionClientImpl;
import com.tencent.sonic.sdk.SonicEngine;
import com.tencent.sonic.sdk.SonicSession;
import com.tencent.sonic.sdk.SonicSessionClient;
import com.tencent.sonic.sdk.SonicSessionConfig;

public class HomeDetailFragment extends BaseFragment {

    private WebView webView;
    private SonicSession sonicSession;
    private SonicSessionClientImpl sonicSessionClient = null;
    private HomeArticleBean.DataBean.DatasBean datasBean;
    private String url;

    public static HomeDetailFragment getInstance(HomeArticleBean.DataBean.DatasBean datasBean){
        Bundle bundle = new Bundle();
        bundle.putParcelable("bean",datasBean);
        HomeDetailFragment homeDetailFragment = new HomeDetailFragment();
        homeDetailFragment.setArguments(bundle);
        return homeDetailFragment;
    }
    @Override
    public int getLayoutID() {
        return R.layout.fragment_detail;
    }

    @Override
    protected void initUI(View view) {
//        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (getArguments() == null)
            return;
        datasBean = getArguments().getParcelable("bean");
        if (datasBean != null){
            url = datasBean.getLink();
        }
        sonicSession = SonicEngine.getInstance().createSession(url,  new SonicSessionConfig.Builder().build());
        if (null != sonicSession) {
            sonicSession.bindClient(sonicSessionClient = new SonicSessionClientImpl());
        } else {
            // this only happen when a same sonic session is already running,
            // u can comment following codes to feedback as a default mode.
//            throw new UnknownError("create session fail!");
//            Toast.makeText(getContext(), "create sonic session fail!", Toast.LENGTH_LONG).show();
        }

        webView = view.findViewById(R.id.web_detail);

    }

    @Override
    protected void initData() {
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (sonicSession != null) {
                    sonicSession.getSessionClient().pageFinish(url);
                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                if (sonicSession != null) {
                    // Call sessionClient.requestResource when host allow the application
                    // to return the local data .
                    return (WebResourceResponse) sonicSession.getSessionClient().requestResource(url);
                }
                return null;
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                return super.shouldInterceptRequest(view, request);
            }
        });

        WebSettings webSettings = webView.getSettings();

        // bind javascript
        // note:if api level lower than 17(android 4.2), addJavascriptInterface has security
        // issue, please use x5 or see https://developer.android.com/reference/android/webkit/
        // WebView.html#addJavascriptInterface(java.lang.Object, java.lang.String)
        webSettings.setJavaScriptEnabled(true);
        webView.removeJavascriptInterface("searchBoxJavaBridge_");
//        intent.putExtra(SonicJavaScriptInterface.PARAM_LOAD_URL_TIME, System.currentTimeMillis());
//        webView.addJavascriptInterface(new SonicJavaScriptInterface(sonicSessionClient, intent), "sonic");

        // init webview settings
        webSettings.setAllowContentAccess(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setSavePassword(false);
        webSettings.setSaveFormData(false);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }
        });

        //  webview is ready now, just tell session client to bind
        if (sonicSessionClient != null) {
            sonicSessionClient.bindView(webView);
            sonicSessionClient.clientReady();
        } else { // default mode
            webView.loadUrl(url);
        }
    }

    @Override
    public boolean onBackPressedSupport() {
        pop();
        return true;
    }

    @Override
    public void onDestroy() {
        if (null != sonicSession) {
            sonicSession.destroy();
            sonicSession = null;
        }
        super.onDestroy();
    }
}
