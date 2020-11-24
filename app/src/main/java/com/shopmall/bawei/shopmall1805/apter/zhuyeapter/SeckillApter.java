package com.shopmall.bawei.shopmall1805.apter.zhuyeapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;

import bean.HomeBean;

public class SeckillApter extends BaseQuickAdapter<HomeBean.SeckillInfoBean.ListBean, BaseViewHolder> {
    public SeckillApter(int layoutResId, @Nullable List<HomeBean.SeckillInfoBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.SeckillInfoBean.ListBean item) {
        Glide.with(mContext).load("http://49.233.0.68:8080/atguigu/img"+item.getFigure()).into((ImageView)helper.getView(R.id.figure));
        helper.setText(R.id.name,item.getName());
    }


//    @Override
//    protected void convert(BaseViewHolder helper, HomeBean.SeckillInfoBean item) {
//        Glide.with(mContext).load("http://49.233.0.68:8080/atguigu/img"+item.getList().get(0).getFigure()).into((ImageView)helper.getView(R.id.figure));
//        helper.setText(R.id.name,item.getList().get(0).getName());
//    }
}
