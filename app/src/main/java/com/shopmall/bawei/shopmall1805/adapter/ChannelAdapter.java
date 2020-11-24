package com.shopmall.bawei.shopmall1805.adapter;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.net.Constants;
import com.example.net.bean.MainBean;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;

public class ChannelAdapter extends BaseQuickAdapter<MainBean.ResultBean.ChannelInfoBean, BaseViewHolder> {
    private Context context;

    public ChannelAdapter(@Nullable List<MainBean.ResultBean.ChannelInfoBean> data, Context context) {
        super(R.layout.item_channel, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MainBean.ResultBean.ChannelInfoBean item) {
        Glide.with(context).load(Constants.BASE_URl_IMAGE+item.getImage()).into((ImageView) helper.getView(R.id.iv_channel));
        helper.setText(R.id.tv_channel,item.getChannel_name());
    }
}
