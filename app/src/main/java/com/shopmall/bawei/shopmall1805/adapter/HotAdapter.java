package com.shopmall.bawei.shopmall1805.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.bean.HomeBean;

import java.util.List;

import baseurl.UrlHelp;

public class HotAdapter extends BaseQuickAdapter<HomeBean.ResultBean.HotInfoBean, BaseViewHolder> {
    public HotAdapter(int layoutResId, @Nullable List<HomeBean.ResultBean.HotInfoBean> data) {
        super(layoutResId, data);
    }



    @Override
    protected void convert(BaseViewHolder helper, HomeBean.ResultBean.HotInfoBean item) {
        ImageView helperView = helper.getView(R.id.hot_im);
        Glide.with(mContext).load(UrlHelp.BASE+UrlHelp.BASE_URl_IMAGE+item.getFigure()).into(helperView);
        helper.setText(R.id.hot_te,item.getName());
        helper.setText(R.id.hot_te2,"￥"+item.getCover_price());
    }
}
