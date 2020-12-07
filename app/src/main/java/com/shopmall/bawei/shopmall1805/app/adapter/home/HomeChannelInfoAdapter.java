package com.shopmall.bawei.shopmall1805.app.adapter.home;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.common.ConfigUrl;
import com.shopmall.bawei.shopmall1805.common.HomeBean;

import java.util.List;

public class HomeChannelInfoAdapter extends BaseQuickAdapter<HomeBean.ResultBean.ChannelInfoBean, BaseViewHolder> {
    public HomeChannelInfoAdapter(int layoutResId, @Nullable List<HomeBean.ResultBean.ChannelInfoBean> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, HomeBean.ResultBean.ChannelInfoBean item) {
        Glide.with(mContext).load(ConfigUrl.BASE_IMAGE+item.getImage()).into((ImageView) helper.getView(R.id.channelInfo_item_img));
        helper.setText(R.id.channelInfo_item_tv,item.getChannel_name());

    }
}
