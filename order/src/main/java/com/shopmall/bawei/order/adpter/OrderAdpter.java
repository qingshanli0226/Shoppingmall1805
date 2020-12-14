package com.shopmall.bawei.order.adpter;

import android.media.Image;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.framework.BaseRVAdapter;
import com.example.net.Confing;
import com.example.net.bean.ShopcarBean;
import com.shopmall.bawei.order.R;

public class OrderAdpter extends BaseRVAdapter<ShopcarBean> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_order;
    }

    @Override
    protected void convert(ShopcarBean itemData, BaseViewHolder baseViewHolder, int position) {
        ImageView imageView = baseViewHolder.getView(R.id.item_img);
        Glide.with(baseViewHolder.itemView.getContext()).load(Confing.BASE_IMAGE+itemData.getUrl()).into(imageView);
        TextView name = baseViewHolder.getView(R.id.item_name);
        name.setText(""+itemData.getProductName());
        TextView num = baseViewHolder.getView(R.id.item_num);
        num.setText("x"+itemData.getProductNum());
        TextView price = baseViewHolder.getView(R.id.item_price);
        float initprice = Float.valueOf(initprice(itemData));
        price.setText(""+initprice);
    }
    //商品的价格变化
    private float initprice(ShopcarBean itemData) {
        String productNum = itemData.getProductNum();
        int num = Integer.parseInt(productNum);
        String productPrice = itemData.getProductPrice();
        float price = Float.valueOf(productPrice);
        float numprice = num * price;
        return numprice;
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
