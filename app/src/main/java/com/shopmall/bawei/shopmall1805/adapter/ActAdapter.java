package com.shopmall.bawei.shopmall1805.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.net.Confing;
import com.example.net.bean.HomeBean;
import com.shopmall.bawei.framework.example.framework.BaseRVAdapter;
import com.shopmall.bawei.shopmall1805.R;


public class ActAdapter extends BaseRVAdapter<HomeBean.ActInfoBean> {


    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_act_layout;
    }

    @Override
    protected void convert(HomeBean.ActInfoBean itemData, BaseViewHolder baseViewHolder, int position) {
        ImageView imageView = baseViewHolder.getView(R.id.vr_act);
//        imageView.setWebViewClient(new WebViewClient());
//        imageView.setWebChromeClient(new WebChromeClient());
//        imageView.loadUrl(Confing.BASE_IMAGE + itemData.getIcon_url());
        Glide.with(baseViewHolder.itemView.getContext()).load(Confing.BASE_IMAGE + itemData.getIcon_url()).into(imageView);
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
