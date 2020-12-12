package com.shopmall.bawei.shopcar;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.net.ConfigUrl;
import com.example.net.ShopcarBean;
import com.shopmall.bawei.shopcar.presenter.ShopcarPresenterImpl;

import java.util.List;

public class ShopCarAdapter extends BaseQuickAdapter<ShopcarBean, BaseViewHolder> {

    private ShopcarPresenterImpl shopcarPresenter;

    public void setShopcarPresenter(ShopcarPresenterImpl shopcarPresenter) {
        this.shopcarPresenter = shopcarPresenter;
    }

    public ShopCarAdapter(int layoutResId, @Nullable List<ShopcarBean> data) {
        super(layoutResId, data);
    }



    @Override
    protected void convert(final BaseViewHolder helper, final ShopcarBean item) {
        Glide.with(mContext).load(ConfigUrl.BASE_IMAGE+item.getUrl()).into((ImageView) helper.getView(R.id.iv_gov));
        helper.setText(R.id.tv_desc_gov,item.getProductName());
        helper.setText(R.id.tv_price_gov,item.getProductPrice()+"$");
        helper.setText(R.id.tv_count,item.getProductNum());


        CheckBox view = helper.getView(R.id.cb_gov);
        helper.addOnClickListener(R.id.cb_gov);
        view.setChecked(item.isProductSelected());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                shopcarPresenter.updateProductSelected(item.getProductId(),view.isSelected(),item.getProductName(),item.getUrl(),String.valueOf(item.getProductPrice()),helper.getPosition());

            }
        });

    }
}
