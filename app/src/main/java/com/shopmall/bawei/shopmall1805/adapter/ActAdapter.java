package com.shopmall.bawei.shopmall1805.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.common.Constants;
import com.shopmall.framework.adapter.BaseRVAdapter;
import com.shopmall.net.bean.HomeData;

public class ActAdapter extends BaseRVAdapter<HomeData.ResultBean.ActInfoBean> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_act_layout;
    }

    @Override
    protected void convert(HomeData.ResultBean.ActInfoBean itemData, BaseViewHolder baseViewHolder, int position) {
        ImageView imageView = baseViewHolder.getView(R.id.actImg);
//        imageView.setWebViewClient(new WebViewClient());
//        imageView.setWebChromeClient(new WebChromeClient());
//        imageView.loadUrl(Constants.BASE_URl_IMAGE+itemData.getIcon_url());
        Glide.with(baseViewHolder.itemView.getContext()).load(Constants.BASE_URl_IMAGE+itemData.getIcon_url()).into(imageView);
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
