package com.shopmall.bawei.shopmall1805.app.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.common.ConfigUrl;
import com.shopmall.bawei.shopmall1805.common.HomeBean;

import java.util.List;

public class SeckillInfoAdapter extends BaseQuickAdapter<HomeBean.ResultBean.SeckillInfoBean.ListBean, BaseViewHolder> {

    public SeckillInfoAdapter(int layoutResId, @Nullable List<HomeBean.ResultBean.SeckillInfoBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.ResultBean.SeckillInfoBean.ListBean item) {
        ImageView helperView = helper.getView(R.id.seckillinfo_item_img);
        Glide.with(mContext).load(ConfigUrl.BASE_IMAGE+item.getFigure()).into(helperView);
        helper.setText(R.id.seckillinfo_item_tv_old,"￥"+item.getCover_price());
        helper.setText(R.id.seckillinfo_item_tv_new,"￥"+item.getOrigin_price());
    }
}
