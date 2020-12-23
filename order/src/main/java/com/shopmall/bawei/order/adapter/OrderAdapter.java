package com.shopmall.bawei.order.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.net.ConfigUrl;
import com.example.net.ShopcarBean;
import com.shopmall.bawei.order.R;
import java.util.List;

public class OrderAdapter extends BaseQuickAdapter<ShopcarBean, BaseViewHolder> {
    public OrderAdapter(int layoutResId, @Nullable List<ShopcarBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopcarBean item) {
        Glide.with(mContext).load(ConfigUrl.BASE_IMAGE+item.getUrl()).into((ImageView) helper.getView(R.id.item_img));
        helper.setText(R.id.item_price,  item.getProductPrice()+"");
        helper.setText(R.id.item_num,item.getProductNum());
        helper.setText(R.id.item_name,item.getProductName());
    }
}
