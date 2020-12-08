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



public class HomeDiscountAdapter extends BaseQuickAdapter<HomeBean.SeckillInfoBean.ListBean, BaseViewHolder> {
    public HomeDiscountAdapter(int layoutResId, @Nullable List<HomeBean.SeckillInfoBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.SeckillInfoBean.ListBean item) {
        ImageView helperView = helper.getView(R.id.you_im);
        Glide.with(mContext).load(UrlHelp.BASE_URl_IMAGE+item.getFigure()).into(helperView);
        helper.setText(R.id.you_te,"￥"+item.getCover_price());
        helper.setText(R.id.you_te2,"￥"+item.getOrigin_price());

        helper.addOnClickListener(R.id.you_im);
        helper.addOnClickListener(R.id.you_te);
    }
}
