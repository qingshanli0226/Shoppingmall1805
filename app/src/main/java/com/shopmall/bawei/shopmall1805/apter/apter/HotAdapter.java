package com.shopmall.bawei.shopmall1805.apter.apter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.apter.apter2.BaseRVAdapter;

import bean.HomeBean;


public class HotAdapter extends BaseRVAdapter<HomeBean.HotInfoBean> {


    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_hot;
    }

    @Override
    protected void convert(HomeBean.HotInfoBean itemData, BaseViewHolder baseViewHolder, int position) {
        ImageView imageView = baseViewHolder.getView(R.id.iv_hot);
        Glide.with(baseViewHolder.itemView.getContext()).load("http://49.233.0.68:8080/atguigu/img/"+ itemData.getFigure()).into(imageView);
        TextView texthot1 = baseViewHolder.getView(R.id.tv_hot_name);
        TextView texthot2 = baseViewHolder.getView(R.id.tv_hot_price);
        texthot1.setText(""+itemData.getName());
        texthot2.setText(""+itemData.getCover_price());
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
