package com.bawei.shopmall.home.view;

import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bawei.common.view.UrlHelper;
import com.bawei.framework.BaseRvAdapter;
import com.bawei.net.mode.HomeBean;
import com.shopmall.bawei.shopmall1805.R;

public class ActAdapter extends BaseRvAdapter<HomeBean.ResultBean.ActInfoBean> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.act_item;
    }

    @Override
    protected void convert(HomeBean.ResultBean.ActInfoBean itemData, BaseViewHolder baseViewHolder, int position) {
        WebView webView = baseViewHolder.getView(R.id.act_viewpager);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        WebSettings settings = webView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.loadUrl(UrlHelper.BASE_RESOURCE_IMAGE_URL + itemData.getIcon_url());
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
