package com.bawei.shopcar;


import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.common.view.NetConfig;
import com.bawei.framework.BaseRvAdapter;
import com.bawei.framework.CacheManager;
import com.bawei.net.mode.ShopcarBean;
import com.bawei.shopcar.presenter.ShopcarPresenterImpl;
import com.bumptech.glide.Glide;


public class ShopcarAdapter extends BaseRvAdapter<ShopcarBean> {
    private boolean isEditMode;

    private ShopcarPresenterImpl shopcarPresenter;

    public void setShopcarPresenter(ShopcarPresenterImpl shopcarPresenter) {
        this.shopcarPresenter = shopcarPresenter;
    }

    public void setEditMode(boolean isEditMode) {
        this.isEditMode = isEditMode;
        notifyDataSetChanged();
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_shop_car;
    }

    @Override
    protected void convert(final ShopcarBean itemData, BaseViewHolder baseViewHolder, final int position) {
        ImageView productImg = baseViewHolder.getView(R.id.productImage);
        Glide.with(baseViewHolder.itemView.getContext()).load(NetConfig.BASE_RESOURCE_IMAGE_URL+itemData.getUrl()).into(productImg);
        TextView productNameTv = baseViewHolder.getView(R.id.productName);
        productNameTv.setText(itemData.getProductName());
        TextView productPriceTv = baseViewHolder.getView(R.id.productPrice);
        productPriceTv.setText((String)itemData.getProductPrice());
        TextView productNumTv = baseViewHolder.getView(R.id.productCount);
        productNumTv.setText(itemData.getProductNum());
        final CheckBox produectSelectCheckBox = baseViewHolder.getView(R.id.productSelect);

        if (!isEditMode) {
            produectSelectCheckBox.setChecked(itemData.isProductSelected());
            initProductSelectCheckBoxClickListener(produectSelectCheckBox, itemData, position);
            initAddDecClickListener(baseViewHolder, itemData, position);
        } else {
            initEditModeCheckBoxClickListener(produectSelectCheckBox, itemData, position);
            if (CacheManager.getInstance().checkIfDataInDeleteShopcarBeanList(itemData)) {
                produectSelectCheckBox.setChecked(true);
            } else {
                produectSelectCheckBox.setChecked(false);
            }
        }


    }

    private void initEditModeCheckBoxClickListener(final CheckBox produectSelectCheckBox, final ShopcarBean itemData, final int position) {
        produectSelectCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (produectSelectCheckBox.isChecked()) {
                    CacheManager.getInstance().addDeleteShopcarBean(itemData, position);
                } else {
                    CacheManager.getInstance().deleteOneShopcarBean(itemData, position);
                }
            }
        });
    }

    private void initAddDecClickListener(BaseViewHolder baseViewHolder, final ShopcarBean itemData, final int position) {
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
                if (oldNum > 1) {
                    int newNum = oldNum - 1;
                    shopcarPresenter.updateProductNum(itemData.getProductId(), String.valueOf(newNum), itemData.getProductName(), itemData.getUrl(), (String) itemData.getProductPrice(),position, String.valueOf(newNum));
                }
            }
        });
    }

    private void initProductSelectCheckBoxClickListener(final CheckBox produectSelectCheckBox, final ShopcarBean itemData, final int position) {
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

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
