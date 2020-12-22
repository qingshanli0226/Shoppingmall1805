package com.shopmall.bawei.shopmall1805.pay.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.pay.R;
import com.shopmall.bawei.shopmall1805.common.ConfigUrl;
import com.shopmall.bawei.shopmall1805.net.entity.ShopcarBean;

import java.util.List;

public class PayAdapter extends BaseQuickAdapter<ShopcarBean, BaseViewHolder> {

    public PayAdapter(int layoutResId, @Nullable List<ShopcarBean> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, ShopcarBean item) {
        Glide.with(mContext).load(ConfigUrl.BASE_IMAGE+item.getUrl()).into((ImageView) helper.getView(R.id.item_pay_url_img));
        helper.setText(R.id.item_pay_message,item.getProductName());
        helper.setText(R.id.item_pay_price,item.getProductPrice());
        helper.setText(R.id.item_pay_single_price,item.getProductPrice());
    }
}
