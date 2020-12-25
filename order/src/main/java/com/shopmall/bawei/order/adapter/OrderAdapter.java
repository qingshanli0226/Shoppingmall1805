package com.shopmall.bawei.order.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.common2.GetShopCarBean;
import com.shopmall.bawei.order.R;

public class OrderAdapter extends BaseRvAdapter<GetShopCarBean> {




    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_order;
    }

    @Override
    protected void convert(final GetShopCarBean itemData, BaseViewHolder helper,final int position) {
        ImageView productImg = helper.getView(R.id.productImage);
        Glide.with(helper.itemView.getContext()).load(itemData.getUrl()).into(productImg);
        TextView productNameTv = helper.getView(R.id.productName);
        productNameTv.setText(itemData.getProductName());
        TextView productPriceTv = helper.getView(R.id.productPrice);
        productPriceTv.setText("￥"+(String)itemData.getProductPrice());
        TextView productNumTv = helper.getView(R.id.productCount);
        productNumTv.setText(itemData.getProductNum());

    }
    @Override
    public int getViewType(int position) {
        return 0;


    }
}
