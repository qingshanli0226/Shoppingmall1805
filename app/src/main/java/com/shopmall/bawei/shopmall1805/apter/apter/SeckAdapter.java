package com.shopmall.bawei.shopmall1805.apter.apter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.apter.apter2.BaseRVAdapter;

import bean.HomeBean;


public class SeckAdapter extends BaseRVAdapter<HomeBean.SeckillInfoBean.ListBean> {


    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_seck_layout;
    }

    @Override
    protected void convert(HomeBean.SeckillInfoBean.ListBean itemData, BaseViewHolder baseViewHolder, int position) {
        ImageView imageView = baseViewHolder.getView(R.id.hotImg);
        Glide.with(baseViewHolder.itemView.getContext()).load("http://49.233.0.68:8080/atguigu/img/"+ itemData.getFigure()).into(imageView);
        TextView texthot1 = baseViewHolder.getView(R.id.tv_hot1);
        TextView texthot2 = baseViewHolder.getView(R.id.tv_hot2);
        texthot1.setText(""+itemData.getCover_price());
        texthot2.setText(""+itemData.getCover_price());
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
