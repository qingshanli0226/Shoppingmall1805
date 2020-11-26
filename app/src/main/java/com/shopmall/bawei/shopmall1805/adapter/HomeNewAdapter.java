package com.shopmall.bawei.shopmall1805.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.shopmall1805.R;
import baseurl.HomeBean;

import java.util.List;

import baseurl.UrlHelp;

public class HomeNewAdapter extends BaseQuickAdapter<HomeBean.ResultBean.RecommendInfoBean, BaseViewHolder> {
    public HomeNewAdapter(int layoutResId, @Nullable List<HomeBean.ResultBean.RecommendInfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.ResultBean.RecommendInfoBean item) {
        ImageView helperView = helper.getView(R.id.xin_im);
        Glide.with(mContext).load(UrlHelp.BASE+UrlHelp.BASE_URl_IMAGE+item.getFigure()).into(helperView);
        helper.setText(R.id.xin_te,item.getName());
        helper.setText(R.id.xin_te2,"￥"+item.getCover_price());
    }





}
