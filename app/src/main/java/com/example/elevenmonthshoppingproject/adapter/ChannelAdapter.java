package com.example.elevenmonthshoppingproject.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.elevenmonthshoppingproject.R;
import com.example.net.Contants;
import com.example.net.bean.Recommonde;

public class ChannelAdapter extends  BaseRVAdapter<Recommonde.ChannelInfoBean> {
    @Override
    protected int getLayoutid(int viewtype) {
        return R.layout.channel_layout;
    }

    @Override
    protected int getViewtype(int postion) {
        return 0;
    }

    @Override
    protected void cover(BaseViewHoder holder, int viewtype, Recommonde.ChannelInfoBean channelInfoBean) {
        ImageView channelimg = holder.getView(R.id.channelImg);
        Glide.with(holder.itemView.getContext()).load(Contants.Gui_Url+channelInfoBean.getImage()).into(channelimg);
}
}
