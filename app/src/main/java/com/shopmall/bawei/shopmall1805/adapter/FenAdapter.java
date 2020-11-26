package com.shopmall.bawei.shopmall1805.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.shopmall1805.R;
import baseurl.HomeBean;

import java.util.List;

import baseurl.UrlHelp;

public class FenAdapter extends BaseQuickAdapter<HomeBean.ResultBean.ChannelInfoBean, BaseViewHolder> {


    public FenAdapter(int layoutResId, @Nullable List<HomeBean.ResultBean.ChannelInfoBean> data) {
        super(layoutResId, data);
    }



    @Override
    protected void convert(BaseViewHolder helper, HomeBean.ResultBean.ChannelInfoBean item) {
        ImageView helperView = helper.getView(R.id.fen_im);
        Glide.with(mContext).load(UrlHelp.BASE+UrlHelp.BASE_URl_IMAGE+item.getImage()).into(helperView);
        helper.setText(R.id.fen_te,item.getChannel_name());
    }
}
