package com.bawei.order.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bawei.common.view.NetConfig;
import com.bawei.net.mode.ShopcarBean;
import com.bawei.order.R;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class OderGoodsAdapter extends BaseQuickAdapter<ShopcarBean, BaseViewHolder> {

    public OderGoodsAdapter(int layoutResId, @Nullable List<ShopcarBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopcarBean item) {
        helper.setText(R.id.item_name, item.getProductName());
        helper.setText(R.id.item_price, "￥" + item.getProductPrice());
        helper.setText(R.id.item_num, item.getProductNum());
        Glide.with(mContext).load(NetConfig.BASE_RESOURCE_IMAGE_URL+item.getUrl()).into((ImageView) helper.getView(R.id.item_img));
    }
}
