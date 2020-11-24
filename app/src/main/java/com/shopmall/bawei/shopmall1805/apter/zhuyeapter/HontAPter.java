package com.shopmall.bawei.shopmall1805.apter.zhuyeapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;

import bean.HomeBean;

public class HontAPter extends BaseQuickAdapter<HomeBean.HotInfoBean, BaseViewHolder> {
    public HontAPter(int layoutResId, @Nullable List<HomeBean.HotInfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.HotInfoBean item) {
        Glide.with(mContext).load("http://49.233.0.68:8080/atguigu/img/"+item.getFigure()).into((ImageView)helper.getView(R.id.image));
        helper.setText(R.id.jieshao,item.getName());
        helper.setText(R.id.price,item.getCover_price());
    }
}
