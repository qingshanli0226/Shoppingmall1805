package com.shopmall.bawei.shopmall1805.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.common.Constants;
import com.shopmall.framework.adapter.BaseRVAdapter;
import com.shopmall.net.bean.HomeData;

public class ChannelAdapter extends BaseRVAdapter<HomeData.ResultBean.ChannelInfoBean> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_channel;
    }

    @Override
    protected void convert(HomeData.ResultBean.ChannelInfoBean itemData, BaseViewHolder baseViewHolder, int position) {
        ImageView imageView = baseViewHolder.getView(R.id.iv_channel);
        Glide.with(baseViewHolder.itemView.getContext())
                .load(Constants.BASE_URl_IMAGE+itemData.getImage()).into(imageView);

        TextView textView = baseViewHolder.getView(R.id.tv_channel);
        textView.setText(itemData.getChannel_name()+"");
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
