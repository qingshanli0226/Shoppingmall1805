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

public class YouAdapter extends BaseQuickAdapter<HomeBean.ResultBean.SeckillInfoBean.ListBean, BaseViewHolder> {
    public YouAdapter(int layoutResId, @Nullable List<HomeBean.ResultBean.SeckillInfoBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.ResultBean.SeckillInfoBean.ListBean item) {
        ImageView helperView = helper.getView(R.id.you_im);
        Glide.with(mContext).load(UrlHelp.BASE+UrlHelp.BASE_URl_IMAGE+item.getFigure()).into(helperView);
        helper.setText(R.id.you_te,"￥"+item.getCover_price());
        helper.setText(R.id.you_te2,"￥"+item.getOrigin_price());
    }
}
