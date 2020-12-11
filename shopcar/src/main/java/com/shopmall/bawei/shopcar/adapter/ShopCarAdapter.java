package com.shopmall.bawei.shopcar.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shopmall.bawei.common.UrlHelper;
import com.shopmall.bawei.framework.BaseRvAdapter;
import com.shopmall.bawei.framework.CacheManager;
import com.shopmall.bawei.framework.view.ClickToCheckInterface;
import com.shopmall.bawei.framework.view.NumberAddSubView;
import com.shopmall.bawei.framework.view.UpdateNumFromAdapter;
import com.shopmall.bawei.net.mode.ShopCarBean;
import com.shopmall.bawei.shopcar.R;
import com.shopmall.bawei.shopcar.contract.ShopCarPresenterImpl;

public class ShopCarAdapter extends BaseRvAdapter<ShopCarBean> {

    private UpdateNumFromAdapter updateNumFromAdapter = new UpdateNumFromAdapter() {
        @Override
        public void onAddNum(ShopCarBean itemData, int num, int position) {
            String oldNum = String.valueOf(num);
            String newNum = String.valueOf(num+1);
            shopCarPresenter.updateProductNum(itemData.getProductId(),oldNum,itemData.getProductName(),itemData.getUrl(),itemData.getProductPrice(),position,newNum);
        }

        @Override
        public void onSubNum(ShopCarBean itemData, int num, int position) {
            if(num > 1){
                String oldNum = String.valueOf(num);
                String newNum = String.valueOf(num-1);
                shopCarPresenter.updateProductNum(itemData.getProductId(),oldNum,itemData.getProductName(),itemData.getUrl(),itemData.getProductPrice(),position,newNum);
            }
        }
    };
    private boolean isEditMode;
    private ShopCarPresenterImpl shopCarPresenter;


    public void setShopCarPresenter(ShopCarPresenterImpl shopCarPresenter) {
        this.shopCarPresenter = shopCarPresenter;
    }

    public void setEditMode(boolean editMode) {
        isEditMode = editMode;
        notifyDataSetChanged();
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_shop_cart;
    }


    @Override
    protected void convert(ShopCarBean itemData, BaseViewHolder baseViewHolder, int position) {
        ImageView productImg = baseViewHolder.getView(R.id.iv_gov);
        Glide.with(baseViewHolder.itemView.getContext()).load(UrlHelper.BASE_URl_IMAGE+itemData.getUrl()).into(productImg);
        TextView productNameTv = baseViewHolder.getView(R.id.tv_desc_gov);
        productNameTv.setText(itemData.getProductName());
        TextView productPriceTv = baseViewHolder.getView(R.id.tv_price_gov);
        productPriceTv.setText("￥"+(String)itemData.getProductPrice());
        NumberAddSubView productNumTv = baseViewHolder.getView(R.id.numberAddSubView);
        productNumTv.setFromAdapter(true);
        productNumTv.setItemData(itemData);
        productNumTv.setPosition(position);
        productNumTv.setUpdateNumFromAdapter(updateNumFromAdapter);
        productNumTv.setValue(Integer.parseInt(itemData.getProductNum()));
        CheckBox productSelectCheckBox = baseViewHolder.getView(R.id.cb_gov);
        CheckBox productDeleteCheckBox = baseViewHolder.getView(R.id.cb_del);
//        productSelectCheckBox.setChecked(itemData.isProductSelected());
        if(!isEditMode){
            productSelectCheckBox.setChecked(itemData.isProductSelected());
            initProductSelectCheckBoxClickListener(productSelectCheckBox,itemData,position);
            productSelectCheckBox.setVisibility(View.VISIBLE);
            productDeleteCheckBox.setVisibility(View.GONE);
        } else {
            productSelectCheckBox.setVisibility(View.GONE);
            productDeleteCheckBox.setVisibility(View.VISIBLE);
            initEditModeCheckBoxClickListener(productDeleteCheckBox, itemData, position);
            if(CacheManager.getInstance().checkIfDataInDeleteShopcarBeanList(itemData)){
                productDeleteCheckBox.setChecked(true);
            } else {
                productDeleteCheckBox.setChecked(false);
            }
        }

    }

    private void initEditModeCheckBoxClickListener(final CheckBox productDeleteCheckBox, final ShopCarBean itemData, final int position){
        productDeleteCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(productDeleteCheckBox.isChecked()){
                    CacheManager.getInstance().addDeleteShopcarBean(itemData,position);
                } else {
                    CacheManager.getInstance().deleteOneShopCarBean(itemData,position);
                }
            }
        });

    }

    private void initProductSelectCheckBoxClickListener(final CheckBox productSelectCheckBox, final ShopCarBean itemData, final int position) {
        productSelectCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = productSelectCheckBox.isChecked();
                shopCarPresenter.updateProductSelected(itemData.getProductId(),checked,itemData.getProductName(),itemData.getUrl(),(String)itemData.getProductPrice(), position);
            }

        });
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
