package com.shopmall.bawei.shopmall1805.home.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.common2.HomeBean;
import com.example.common2.UrlHelp;
import com.shopmall.bawei.shopmall1805.R;


import java.util.List;



public class HomeClassificationAdapter extends BaseQuickAdapter<HomeBean.ChannelInfoBean, BaseViewHolder> {


    public HomeClassificationAdapter(int layoutResId, @Nullable List<HomeBean.ChannelInfoBean> data) {
        super(layoutResId, data);
    }



    @Override
    protected void convert(BaseViewHolder helper, HomeBean.ChannelInfoBean item) {
        ImageView helperView = helper.getView(R.id.fen_im);
        Glide.with(mContext).load(UrlHelp.BASE_URl_IMAGE+item.getImage()).into(helperView);
        helper.setText(R.id.fen_te,item.getChannel_name());
        helper.addOnClickListener(R.id.fen_im);
        helper.addOnClickListener(R.id.fen_te);
    }
}
