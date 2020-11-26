package com.shopmall.bawei.shopmall1805.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.net.ConfigUrl;
import com.example.net.HomeBean;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;

public class ItemFiveAdapter extends BaseQuickAdapter<HomeBean.RecommendInfoBean, BaseViewHolder> {

    public ItemFiveAdapter(int layoutResId, @Nullable List<HomeBean.RecommendInfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.RecommendInfoBean item) {
        Glide.with(mContext).load(ConfigUrl.BASE_IMAGE+item.getFigure()).into((ImageView) helper.getView(R.id.imv_item5));
    }
}
