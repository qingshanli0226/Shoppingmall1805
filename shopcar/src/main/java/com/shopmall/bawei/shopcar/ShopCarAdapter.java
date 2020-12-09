package com.shopmall.bawei.shopcar;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.net.ConfigUrl;
import com.example.net.ShopcarBean;

import java.util.List;

public class ShopCarAdapter extends BaseQuickAdapter<ShopcarBean, BaseViewHolder> {


    public ShopCarAdapter(int layoutResId, @Nullable List<ShopcarBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopcarBean item) {
        Glide.with(mContext).load(ConfigUrl.BASE_IMAGE+item.getUrl()).into((ImageView) helper.getView(R.id.iv_gov));
        helper.setText(R.id.tv_desc_gov,item.getProductName());
        helper.setText(R.id.tv_price_gov,item.getProductNum());
    }
}
