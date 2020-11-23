package com.shopmall.bawei.shopmall1805.Adper;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;

import mode.HomeBean;
import view.Constants;

public
class channelAdper extends BaseQuickAdapter<HomeBean.ResultBean.ChannelInfoBean, BaseViewHolder> {

    public channelAdper(int layoutResId, @Nullable List<HomeBean.ResultBean.ChannelInfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.ResultBean.ChannelInfoBean item) {
        Glide.with(mContext).load(Constants.BASE_URl_IMAGE+item.getImage())
                .into((ImageView)helper.getView(R.id.channel_Image_one));

        helper.setText(R.id.channel_text_tow,item.getChannel_name());
    }
}
