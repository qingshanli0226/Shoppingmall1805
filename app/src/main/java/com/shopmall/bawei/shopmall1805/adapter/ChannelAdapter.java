package com.shopmall.bawei.shopmall1805.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.net.Confing;
import com.example.net.bean.HomeBean;
import com.shopmall.bawei.framework.example.framework.BaseRVAdapter;
import com.shopmall.bawei.shopmall1805.R;


public class ChannelAdapter extends BaseRVAdapter<HomeBean.ChannelInfoBean> {


    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_channel_data;
    }

    @Override
    protected void convert(HomeBean.ChannelInfoBean itemData, BaseViewHolder baseViewHolder, int position) {
        ImageView imageView = baseViewHolder.getView(R.id.iv_channel);
        Glide.with(baseViewHolder.itemView.getContext()).load(Confing.BASE_IMAGE + itemData.getImage()).into(imageView);
//        TextView viewById = baseViewHolder.itemView.findViewById(R.id.iv_channel);
        TextView textview = baseViewHolder.getView(R.id.tv_channel);
        textview.setText(""+itemData.getChannel_name());
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
