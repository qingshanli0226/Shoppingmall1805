package com.shopmall.bawei.shopmall1805.home.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.common2.HomeBean;
import com.example.common2.UrlHelp;
import com.shopmall.bawei.shopmall1805.R;


import java.util.List;



public class HomeHotAdapter extends BaseQuickAdapter<HomeBean.HotInfoBean, BaseViewHolder> {
    public HomeHotAdapter(int layoutResId, @Nullable List<HomeBean.HotInfoBean> data) {
        super(layoutResId, data);
    }



    @Override
    protected void convert(BaseViewHolder helper, HomeBean.HotInfoBean item) {
        ImageView helperView = helper.getView(R.id.hot_im);
        Glide.with(mContext).load(UrlHelp.BASE_URl_IMAGE+item.getFigure()).into(helperView);
        helper.setText(R.id.hot_te,item.getName());
        helper.setText(R.id.hot_te2,"￥"+item.getCover_price());
        helper.addOnClickListener(R.id.hot_im);
        helper.addOnClickListener(R.id.hot_te);
    }
}
