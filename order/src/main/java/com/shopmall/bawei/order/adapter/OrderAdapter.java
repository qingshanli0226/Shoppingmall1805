package com.shopmall.bawei.order.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.shopmall.bawei.framework.base.BaseRVAdapter;
import com.shopmall.bawei.order.R;
import com.shopmall.bean.ShopcarBean;

public class OrderAdapter extends BaseRVAdapter<ShopcarBean.ResultBean> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_order_shop;
    }

    @Override
    protected void convert(ShopcarBean.ResultBean itemData, BaseViewHolder baseViewHolder, int position) {
        ImageView image = baseViewHolder.itemView.findViewById(R.id.item_order_image);
        TextView num = baseViewHolder.itemView.findViewById(R.id.item_order_num);
        TextView money = baseViewHolder.itemView.findViewById(R.id.item_order_money);
        TextView name = baseViewHolder.itemView.findViewById(R.id.item_order_name);
        Glide.with(baseViewHolder.itemView.getContext())
                .load(itemData.getUrl())
                .transform(new CenterCrop())
                .into(image);
        num.setText("×"+itemData.getProductNum());
        money.setText("￥"+itemData.getProductPrice());
        name.setText(itemData.getProductName());
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
