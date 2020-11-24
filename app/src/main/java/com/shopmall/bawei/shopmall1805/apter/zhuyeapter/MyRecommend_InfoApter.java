package com.shopmall.bawei.shopmall1805.apter.zhuyeapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;

import bean.HomeBean;

public class MyRecommend_InfoApter extends BaseQuickAdapter<HomeBean.RecommendInfoBean, BaseViewHolder> {
    public MyRecommend_InfoApter(int layoutResId, @Nullable List<HomeBean.RecommendInfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.RecommendInfoBean item) {
        Glide.with(mContext).load("http://49.233.0.68:8080/atguigu/img"+item.getFigure()).into((ImageView)helper.getView(R.id.image));
        helper.setText(R.id.jieshao,item.getName());
        helper.setText(R.id.price,item.getCover_price());
    }
}
