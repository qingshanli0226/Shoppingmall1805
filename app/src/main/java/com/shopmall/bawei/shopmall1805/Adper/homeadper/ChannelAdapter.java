package com.shopmall.bawei.shopmall1805.Adper.homeadper;



import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shopmall.bawei.shopmall1805.R;

import framework.BaseRVAdapter;
import framework.BaseRvAdper;
import mode.HomeBean;
import view.Constants;


public class ChannelAdapter extends BaseRvAdper<HomeBean.ResultBean.ChannelInfoBean> {

    @Override
    protected int getlayourId(int i) {
        return R.layout.item_channel_data;
    }

    @Override
    protected void convert(HomeBean.ResultBean.ChannelInfoBean itemData, BaseviewHoder baseViewHolder, int position) {
        ImageView imageView = baseViewHolder.getView(R.id.iv_channel);
        Glide.with(baseViewHolder.itemView.getContext()).load(Constants.BASE_URl_IMAGE + itemData.getImage()).into(imageView);
        TextView textview = baseViewHolder.getView(R.id.tv_channel);
        textview.setText(""+itemData.getChannel_name());
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
