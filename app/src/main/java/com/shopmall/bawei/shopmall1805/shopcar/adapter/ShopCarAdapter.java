package com.shopmall.bawei.shopmall1805.shopcar.adapter;

import android.media.tv.TvTrackInfo;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.common2.GetShopCarBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.goods.view.GoodsCount;

import java.util.List;

public class    ShopCarAdapter extends BaseQuickAdapter<GetShopCarBean, BaseViewHolder> {
    public ShopCarAdapter(int layoutResId, @Nullable List<GetShopCarBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetShopCarBean item) {
         CheckBox shopradio = helper.getView(R.id.shop_Radio);
        ImageView shopcaradd = helper.getView(R.id.shop_car_add);
        ImageView shopcarcut = helper.getView(R.id.shop_car_cut);
        ImageView shopimage = helper.getView(R.id.shop_car_image);

        helper.setText(R.id.shop_car_text,item.getProductName());

        helper.setText(R.id.shop_car_pay,"￥"+item.getProductPrice());

        helper.setText(R.id.shop_car_count,item.getProductNum());
        helper.addOnClickListener(R.id.shop_Radio);
        Glide.with(mContext).load(item.getUrl()).into(shopimage);





    }
}
