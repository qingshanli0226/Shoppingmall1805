package com.example.shopercar.view;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.common.ShopMallContants;
import com.example.framwork.BaseRVAdapter;
import com.example.framwork.CacheManager;
import com.example.net.bean.ShopcarBean;
import com.example.shopercar.R;
import com.example.shopercar.presenter.ShopCarPresenterImpl;

public class ShopcarAdapter extends BaseRVAdapter<ShopcarBean> {
    private boolean isEditMode;//是否是编辑模式

    private ShopCarPresenterImpl shopCarPresenter;

    public  void setShopcarAdapter(ShopCarPresenterImpl shopCarPresenter) {
        this.shopCarPresenter = shopCarPresenter;
    }

    public void setEditMode(boolean isEditMode) {
        this.isEditMode = isEditMode;
        notifyDataSetChanged();
    }

    @Override
    protected int getLayoutid(int viewtype) {
        return R.layout.item_shop_car;
    }

    @Override
    protected int getViewtype(int postion) {
        return 0;
    }

    @Override
    protected void cover(BaseViewHoder holder, int viewtype, ShopcarBean shopcarBean) {
        ImageView productImg = holder.getView(R.id.productImage);
        Glide.with(holder.itemView.getContext()).load(shopcarBean.getUrl()).into(productImg);
        TextView productNameTv = holder.getView(R.id.productName);
        productNameTv.setText(shopcarBean.getProductName());
        TextView productPriceTv = holder.getView(R.id.productPrice);
        productPriceTv.setText((String)shopcarBean.getProductPrice());
        TextView productNumTv = holder.getView(R.id.productCount);
        productNumTv.setText(shopcarBean.getProductNum());
        final CheckBox produectSelectCheckBox = holder.getView(R.id.productSelect);

        if (!isEditMode) {//正常模式
            produectSelectCheckBox.setChecked(shopcarBean.isProductSelected());
            initProductSelectCheckBoxClickListener(produectSelectCheckBox, shopcarBean, viewtype);
            initAddDecClickListener(holder, shopcarBean, viewtype);
        } else {//编辑模式
            initEditModeCheckBoxClickListener(produectSelectCheckBox, shopcarBean, viewtype);
        }
    }

    private void initEditModeCheckBoxClickListener(final CheckBox produectSelectCheckBox, final ShopcarBean shopcarBean, final int viewtype) {
        produectSelectCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (produectSelectCheckBox.isChecked()){
                    CacheManager.getInstance().addDeleteShopcarBean(shopcarBean,viewtype);
                }else {
                    CacheManager.getInstance().deleteOneShopcarBean(shopcarBean,viewtype);
                }
            }
        });

    }

    private void initAddDecClickListener(BaseViewHoder holder, final ShopcarBean shopcarBean, final int viewtype) {
        holder.getView(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //先获取之前产品的数量
                int oldNum = Integer.parseInt(shopcarBean.getProductNum());
                int newNum = oldNum+1;
                shopCarPresenter.updateProductNum(shopcarBean.getProductId(), String.valueOf(newNum), shopcarBean.getProductName(), shopcarBean.getUrl(),(String) shopcarBean.getProductPrice(), viewtype, String.valueOf(newNum));
            }
        });
        holder.getView(R.id.btnSub).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //先获取之前产品的数量
                int oldNum = Integer.parseInt(shopcarBean.getProductNum());
                if (oldNum > 1) {//确保购物车上的商品数量大于或者等于1
                    int newNum = oldNum - 1;
                    shopCarPresenter.updateProductNum(shopcarBean.getProductId(), String.valueOf(newNum), shopcarBean.getProductName(), shopcarBean.getUrl(), (String) shopcarBean.getProductPrice(),viewtype, String.valueOf(newNum));
                }
            }
        });
    }

    private void initProductSelectCheckBoxClickListener(final CheckBox produectSelectCheckBox, final ShopcarBean shopcarBean, final int viewtype) {
            produectSelectCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean newSelect;
                    if (produectSelectCheckBox.isChecked()){
                        newSelect=false;
                    }else {
                        newSelect=true;
                    }

                    shopCarPresenter.updateProductSelected(shopcarBean.getProductId(),newSelect,shopcarBean.getProductName(),shopcarBean.getUrl(),(String)shopcarBean.getProductPrice(), viewtype);

                }
            });

    }
}
