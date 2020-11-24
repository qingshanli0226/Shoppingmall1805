package com.shopmall.bawei.shopmall1805.home.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.net.Contants;
import com.bw.net.bean.HomeFragmentBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.shopmall1805.R;


import java.util.List;

public class MyChannelAdapter extends BaseQuickAdapter<HomeFragmentBean.ResultBean.ChannelInfoBean, BaseViewHolder> {
    public MyChannelAdapter(int layoutResId, @Nullable List<HomeFragmentBean.ResultBean.ChannelInfoBean> data) {
        super(layoutResId, data);
    }



    @Override
    protected void convert(BaseViewHolder helper, HomeFragmentBean.ResultBean.ChannelInfoBean item) {
        ImageView imageView = helper.getView(R.id.channel_image);
        Glide.with(mContext).load(Contants.BASE_URl_IMAGE+item.getImage()).into(imageView);
        helper.setText(R.id.channel_title,item.getChannel_name()+"");
    }
}
