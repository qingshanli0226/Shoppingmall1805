package com.shopmall.bawei.pay.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.net.bean.ShopcarBean;
import com.shopmall.bawei.framework.example.framework.BaseRVAdapter;
import com.shopmall.bawei.pay.R;

public class PayAdapter extends BaseRVAdapter<ShopcarBean> {

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_order;
    }

    @Override
    protected void convert(ShopcarBean itemData, BaseViewHolder baseViewHolder, int position) {
        ImageView orderImg = baseViewHolder.getView(R.id.orderImg);
        TextView orderName = baseViewHolder.getView(R.id.orderName);
        TextView orderPrice = baseViewHolder.getView(R.id.orderPrice);
        TextView orderNum = baseViewHolder.getView(R.id.orderNum);

        Glide.with(baseViewHolder.itemView.getContext()).load(itemData.getUrl()).into(orderImg);
        orderName.setText(itemData.getProductName());
        orderPrice.setText(itemData.getProductPrice());
        orderNum.setText(itemData.getProductNum());

    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
