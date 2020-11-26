package com.shopmall.bawei.shopmall1805.Adper.homeadper;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shopmall.bawei.shopmall1805.R;

import framework.BaseRvAdper;
import mode.HomeBean;
import view.Constants;

class HotAdapter extends BaseRvAdper<HomeBean.ResultBean.HotInfoBean> {
    @Override
    protected int getlayourId(int i) {
        return R.layout.item_hot;
    }

    @Override
    protected void convert(HomeBean.ResultBean.HotInfoBean itemData, BaseviewHoder baseViewHolder, int position) {
        ImageView imageView = baseViewHolder.getView(R.id.iv_hot);
        Glide.with(baseViewHolder.itemView.getContext()).load(Constants.BASE_URl_IMAGE + itemData.getFigure()).into(imageView);
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
