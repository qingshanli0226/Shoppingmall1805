package com.shopmall.bawei.shopmall1805.shopcar.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.common2.GetShopCarBean;
import com.example.common2.UrlHelp;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.adapter.BaseRvAdapter;
import com.shopmall.bawei.shopmall1805.shopcar.presenter.ShopCarPresenter;

import java.util.List;

import mvp.CacheManager;

public class   ShopCarAdapter extends BaseRvAdapter<GetShopCarBean> {

    private boolean changeEditMode;
    private ShopCarPresenter shopcarPresenter;
    public void setShopcarPresenter(ShopCarPresenter shopcarPresenter) {
        this.shopcarPresenter = shopcarPresenter;
    }

    public void changeEditMode(boolean isEditMode) {
        this.changeEditMode = isEditMode;
        notifyDataSetChanged();//刷新列表
    }
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_shopcar;
    }

    @Override
    protected void convert(final GetShopCarBean itemData, BaseViewHolder helper,final int position) {
        ImageView productImg = helper.getView(R.id.productImage);
        Glide.with(helper.itemView.getContext()).load(itemData.getUrl()).into(productImg);
        TextView productNameTv = helper.getView(R.id.productName);
        productNameTv.setText(itemData.getProductName());
        TextView productPriceTv = helper.getView(R.id.productPrice);
        productPriceTv.setText((String)itemData.getProductPrice());
        TextView productNumTv = helper.getView(R.id.productCount);
        productNumTv.setText(itemData.getProductNum());
        final CheckBox produectSelectCheckBox = helper.getView(R.id.productSelect);

        if (!changeEditMode) {//正常模式
            produectSelectCheckBox.setChecked(itemData.isProductSelected());
            initProductSelectCheckBoxClickListener(produectSelectCheckBox, itemData, position);
            initAddDecClickListener(helper, itemData, position);
        } else {//编辑模式
            initEditModeCheckBoxClickListener(produectSelectCheckBox, itemData, position);
            if (CacheManager.getInstance().checkIfDataInDeleteShopcarBeanList(itemData)) {
                produectSelectCheckBox.setChecked(true);
            } else {
                produectSelectCheckBox.setChecked(false);
            }
        }
    }




    private void initEditModeCheckBoxClickListener(final CheckBox produectSelectCheckBox, final GetShopCarBean itemData, final int position) {
        produectSelectCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (produectSelectCheckBox.isChecked()) {
                    //当前在编辑模式下，选择了该商品，需要把该商品添加到删除队列中
                    CacheManager.getInstance().addDeleteShopcarBean(itemData, position);
                } else {
                    //在编辑模式下，该商品由已选择变为未选择，那么需要把它从删除队列中删除
                    CacheManager.getInstance().deleteOneShopcarBean(itemData, position);
                }
            }
        });


    }

    private void initProductSelectCheckBoxClickListener(final CheckBox produectSelectCheckBox, final GetShopCarBean itemData, final int position) {
        produectSelectCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean newSelected;
                if (produectSelectCheckBox.isChecked()) {
                    newSelected = false;
                } else {
                    newSelected = true;
                }
                shopcarPresenter.updateProductSelected(itemData.getProductId(),newSelected,itemData.getProductName(),itemData.getUrl(),(String)itemData.getProductPrice(), position);
            }
        });
    }
    private void initAddDecClickListener(BaseViewHolder baseViewHolder, final GetShopCarBean itemData, final int position) {
        baseViewHolder.getView(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int oldNum = Integer.parseInt(itemData.getProductNum());
                int newNum = oldNum+1;
                shopcarPresenter.updateProductNum(itemData.getProductId(), String.valueOf(newNum), itemData.getProductName(), itemData.getUrl(),(String) itemData.getProductPrice(), position, String.valueOf(newNum));
            }
        });

        baseViewHolder.getView(R.id.btnSub).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int oldNum = Integer.parseInt(itemData.getProductNum());
                if (oldNum > 1) {//确保购物车上的商品数量大于或者等于1
                    int newNum = oldNum - 1;
                    shopcarPresenter.updateProductNum(itemData.getProductId(), String.valueOf(newNum), itemData.getProductName(), itemData.getUrl(), (String) itemData.getProductPrice(),position, String.valueOf(newNum));
                }
            }
        });
    }


    @Override
    public int getViewType(int position) {
        return 0;


    }
}
