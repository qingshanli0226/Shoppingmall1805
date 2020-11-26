package com.shopmall.bawei.shopmall1805.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;

import baseurl.HomeBean;
import baseurl.SkirstBean;
import baseurl.UrlHelp;

public class Hot_Adapter extends BaseQuickAdapter<SkirstBean.ResultBean.HotProductListBean, BaseViewHolder> {
    public Hot_Adapter(int layoutResId, @Nullable List<SkirstBean.ResultBean.HotProductListBean> data) {
        super(layoutResId, data);
    }



    @Override
    protected void convert(BaseViewHolder helper, SkirstBean.ResultBean.HotProductListBean item) {
        ImageView helperView = helper.getView(R.id.hot_class_im);
        Glide.with(mContext).load(UrlHelp.BASE+UrlHelp.BASE_URl_IMAGE+item.getFigure()).into(helperView);
        helper.setText(R.id.hot_class_te,"￥"+item.getCover_price());

    }
}
