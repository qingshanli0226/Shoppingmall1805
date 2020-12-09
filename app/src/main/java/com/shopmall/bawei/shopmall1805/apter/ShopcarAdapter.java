package com.shopmall.bawei.shopmall1805.apter;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.apter.apter2.BaseRVAdapter;

import java.util.List;

import bean.Shoppingcartproducts;

public class ShopcarAdapter extends BaseRVAdapter<Shoppingcartproducts.ResultBean> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.iteam_shopcar;
    }

    @Override
    protected void convert(Shoppingcartproducts.ResultBean itemData, BaseViewHolder baseViewHolder, int position) {

        TextView productName = baseViewHolder.getView(R.id.productName);
        TextView productPrice = baseViewHolder.getView(R.id.productPrices);
        TextView productCount = baseViewHolder.getView(R.id.productNums);

        productName.setText(""+itemData.getProductName());
        productPrice.setText(""+itemData.getProductPrice());
        productCount.setText(itemData.getProductNum()+"");
        ImageView imageView = baseViewHolder.getView(R.id.productImage);
        Glide.with(baseViewHolder.itemView.getContext()).load(itemData.getUrl()).into(imageView);
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }

//    public ShopcarAdapter(int layoutResId, @Nullable List<Shoppingcartproducts.ResultBean> data) {
//        super(layoutResId, data);
//    }
//
//    @Override
//    protected void convert(BaseViewHolder helper, Shoppingcartproducts.ResultBean item) {

//    }
}
