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



public class HomeNewAdapter extends BaseQuickAdapter<HomeBean.RecommendInfoBean, BaseViewHolder> {
    public HomeNewAdapter(int layoutResId, @Nullable List<HomeBean.RecommendInfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.RecommendInfoBean item) {
        ImageView helperView = helper.getView(R.id.xin_im);
        Glide.with(mContext).load(UrlHelp.BASE_URl_IMAGE+item.getFigure()).into(helperView);
        helper.setText(R.id.xin_te,item.getName());
        helper.setText(R.id.xin_te2,"￥"+item.getCover_price());
        helper.addOnClickListener(R.id.xin_im);
        helper.addOnClickListener(R.id.xin_te);
    }





}
