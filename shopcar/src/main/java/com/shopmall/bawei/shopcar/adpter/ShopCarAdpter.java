package com.shopmall.bawei.shopcar.adpter;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.framework.BaseRVAdapter;
import com.example.net.Confing;
import com.example.net.bean.ShopcarBean;
import com.shopmall.bawei.shopcar.R;

public class ShopCarAdpter extends BaseRVAdapter<ShopcarBean> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_shopcar;
    }

    @Override
    protected void convert(ShopcarBean itemData, BaseViewHolder baseViewHolder, int position) {
        ImageView iv_gov = baseViewHolder.getView(R.id.iv_gov);
        Glide.with(baseViewHolder.itemView.getContext()).load(Confing.BASE_IMAGE+itemData.getUrl()).into(iv_gov);
        TextView textname = baseViewHolder.getView(R.id.tv_desc_gov);
        textname.setText(""+itemData.getProductName());
        TextView textprice = baseViewHolder.getView(R.id.tv_price_gov);
        textprice.setText(""+itemData.getProductPrice());
        TextView textcount = baseViewHolder.getView(R.id.tv_count);
        textcount.setText(""+itemData.getProductNum());
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
