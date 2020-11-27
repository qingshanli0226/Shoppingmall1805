package com.shopmall.bawei.shopmall1805.homeadapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shopmall.bawei.common.Constants;
import com.shopmall.bawei.framework.base.BaseRVAdapter;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bean.HomeData;


public class ChannelAdapter extends BaseRVAdapter<HomeData.ResultBean.ChannelInfoBean> {


    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_channel_data;
    }

    @Override
    protected void convert(HomeData.ResultBean.ChannelInfoBean itemData, BaseViewHolder baseViewHolder, int position) {
        ImageView imageView = baseViewHolder.getView(R.id.iv_channel);
        Glide.with(baseViewHolder.itemView.getContext()).load(Constants.BASE_URl_IMAGE + itemData.getImage()).into(imageView);
        TextView viewById = baseViewHolder.itemView.findViewById(R.id.iv_channel);
        TextView textview = baseViewHolder.getView(R.id.tv_channel);
        textview.setText(""+itemData.getChannel_name());
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
