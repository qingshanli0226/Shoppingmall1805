package com.shopmall.bawei.shopmall1805.shopcar.adapter;

import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.shopcar.R;
import com.shopmall.bawei.shopmall1805.common.ConfigUrl;
import com.shopmall.bawei.shopmall1805.net.entity.ShopcarBean;

import java.util.List;

public class ShopCarAdapter extends BaseQuickAdapter<ShopcarBean, BaseViewHolder> {

    public ShopCarAdapter(int layoutResId, @Nullable List<ShopcarBean> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, ShopcarBean item) {
        helper.setChecked(R.id.item_shopcar_rb,item.isProductSelected());
        Glide.with(mContext).load(ConfigUrl.BASE_IMAGE+item.getUrl()).into((ImageView) helper.getView(R.id.item_shopcar_img));
        helper.setText(R.id.item_shopcar_title_tv,item.getProductName());
        helper.setText(R.id.item_shopcar_price_tv,item.getProductPrice());

    }
}
