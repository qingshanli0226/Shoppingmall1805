package com.shopmall.bawei.shopmall1805.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.net.ConfigUrl;
import com.example.net.HomeBean;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;

public class ItemPrimereAdapter extends BaseQuickAdapter<HomeBean.ChannelInfoBean, BaseViewHolder> {


    public ItemPrimereAdapter(int layoutResId, @Nullable List<HomeBean.ChannelInfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.ChannelInfoBean item) {
        helper.setText(R.id.tv_item2,item.getChannel_name());
        ImageView view = helper.getView(R.id.imv_item2);
        Glide.with(mContext).load(ConfigUrl.BASE_IMAGE+item.getImage()).into(view);
    }
}
