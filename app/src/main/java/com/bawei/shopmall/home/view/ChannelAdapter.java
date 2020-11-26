package com.bawei.shopmall.home.view;

import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.common.view.UrlHelper;
import com.bawei.framework.BaseRvAdapter;
import com.bawei.net.mode.HomeBean;
import com.bumptech.glide.Glide;
import com.shopmall.bawei.shopmall1805.R;

public class ChannelAdapter extends BaseRvAdapter<HomeBean.ResultBean.ChannelInfoBean> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_channel_layout;
    }

    @Override
    protected void convert(HomeBean.ResultBean.ChannelInfoBean itemData, BaseViewHolder baseViewHolder, int position) {
        TextView tv_channel = baseViewHolder.getView(R.id.tv_channel);
        tv_channel.setText(itemData.getChannel_name());
        Glide.with(baseViewHolder.itemView.getContext()).load(UrlHelper.BASE_RESOURCE_IMAGE_URL+itemData.getImage()).into((ImageView)baseViewHolder.getView(R.id.channelImg));
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
