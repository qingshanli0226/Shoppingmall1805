package com.shopmall.bawei.shopmall1805.apter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.apter.apter2.BaseRVAdapter;

import bean.Shoppingcartproducts;

public class ThrorderApter extends BaseRVAdapter<Shoppingcartproducts.ResultBean> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_order;
    }

    @Override
    protected void convert(Shoppingcartproducts.ResultBean itemData, BaseViewHolder baseViewHolder, int position) {
        ImageView imageView = baseViewHolder.getView(R.id.item_img);
        Glide.with(baseViewHolder.itemView.getContext()).load(itemData.getUrl()).into(imageView);
        TextView productName = baseViewHolder.getView(R.id.item_name);
        TextView productPrice = baseViewHolder.getView(R.id.item_price);
        TextView productCount = baseViewHolder.getView(R.id.item_num);
        productName.setText(""+itemData.getProductName());
        productPrice.setText(""+itemData.getProductPrice());
        productCount.setText(itemData.getProductNum()+"");

    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
