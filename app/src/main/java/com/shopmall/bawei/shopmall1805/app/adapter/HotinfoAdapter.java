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

public class HotinfoAdapter extends BaseQuickAdapter<HomeBean.ResultBean.HotInfoBean, BaseViewHolder> {

    public HotinfoAdapter(int layoutResId, @Nullable List<HomeBean.ResultBean.HotInfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.ResultBean.HotInfoBean item) {
        Glide.with(mContext).load(ConfigUrl.BASE_IMAGE+item.getFigure()).into((ImageView) helper.getView(R.id.hotinfo_item_img));
        helper.setText(R.id.hotinfo_item_tv_new,item.getName());
        helper.setText(R.id.hotinfo_item_tv_old,item.getCover_price());
    }
}
