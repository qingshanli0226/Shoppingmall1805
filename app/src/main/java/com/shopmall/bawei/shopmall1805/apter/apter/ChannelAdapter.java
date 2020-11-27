package com.shopmall.bawei.shopmall1805.apter.apter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.apter.apter2.BaseRVAdapter;

import bean.HomeBean;


public class ChannelAdapter extends BaseRVAdapter<HomeBean.ChannelInfoBean> {


    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_channel_data;
    }

    @Override
    protected void convert(HomeBean.ChannelInfoBean itemData, BaseViewHolder baseViewHolder, int position) {
        ImageView imageView = baseViewHolder.getView(R.id.iv_channel);
        Glide.with(baseViewHolder.itemView.getContext()).load("http://49.233.0.68:8080/atguigu/img/"+ itemData.getImage()).into(imageView);
        TextView viewById = baseViewHolder.itemView.findViewById(R.id.iv_channel);
        TextView textview = baseViewHolder.getView(R.id.tv_channel);
        textview.setText(""+itemData.getChannel_name());
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
