package com.shopmall.bawei.shopmall1805.Adper.homeadper;


import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shopmall.bawei.shopmall1805.R;

import framework.BaseRvAdper;
import mode.HomeBean;
import view.Constants;

public class ActAdapter extends BaseRvAdper<HomeBean.ResultBean.ActInfoBean> {

    @Override
    protected int getlayourId(int i) {
        return R.layout.item_act_layout;
    }

    @Override
    protected void convert(HomeBean.ResultBean.ActInfoBean itemData, BaseviewHoder baseViewHolder, int position) {
        ImageView imageView = baseViewHolder.getView(R.id.vr_act);
        Glide.with(baseViewHolder.itemView.getContext()).load(Constants.BASE_URl_IMAGE + itemData.getIcon_url()).into(imageView);
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
