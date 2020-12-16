package com.shopmall.bawei.shopcar;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.shopmall.framework.adapter.BaseRVAdapter;
import com.shopmall.framework.manager.CacheManager;
import com.shopmall.framework.service.ShopCarNet;
import com.shopmall.net.bean.ShopcarBean;
import com.shopmall.net.glide.Myglide;

public class ShopCarAdapter extends BaseRVAdapter<ShopcarBean.ResultBean> {
    private boolean isSelect;

    public void setIsSelect(boolean isSelect) {
        this.isSelect = isSelect;
        notifyDataSetChanged();
    }

    private CheckBox checkBox;
    private ImageView image;
    private TextView name;
    private TextView money;
    private TextView jian;
    private TextView num;
    private TextView jia;

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.shopcar_item;
    }

    @Override
    protected void convert(ShopcarBean.ResultBean itemData, BaseViewHolder baseViewHolder, int position) {
        checkBox = baseViewHolder.itemView.findViewById(R.id.item_shopCar_checkbox);
        image = baseViewHolder.itemView.findViewById(R.id.item_shopCar_image);
        name = baseViewHolder.itemView.findViewById(R.id.item_shopCar_name);
        money = baseViewHolder.itemView.findViewById(R.id.item_shopCar_money);
        jian = baseViewHolder.itemView.findViewById(R.id.item_shopCar_jian);
        num = baseViewHolder.itemView.findViewById(R.id.item_shopCar_num);
        jia = baseViewHolder.itemView.findViewById(R.id.item_shopCar_jia);

        name.setText(itemData.getProductName());
        money.setText("￥"+itemData.getProductPrice());
        num.setText(itemData.getProductNum());
        Myglide.getMyglide().centercenglide(baseViewHolder.itemView.getContext(), image,itemData.getUrl());

        if (isSelect){
            editConvert(itemData,baseViewHolder,position);
        }else {
            finishConvert(itemData,baseViewHolder,position);
        }

    }

    private void finishConvert(final ShopcarBean.ResultBean itemData, BaseViewHolder baseViewHolder, final int position) {
        CacheManager.getInstance().delteclear();
        if (itemData.isProductSelected()){
            checkBox.setChecked(true);
        }else {
            checkBox.setChecked(false);
        }

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopCarNet.getShopCarNet().updateProductSelected(itemData,position);
            }
        });

        jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int oldNum = Integer.parseInt(itemData.getProductNum());
                int newNum = oldNum+1;
                ShopCarNet.getShopCarNet().updateProductNum(String.valueOf(newNum),itemData,position);
            }
        });

        jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int oldNum = Integer.parseInt(itemData.getProductNum());
                if (oldNum >1){
                    int newNum = oldNum-1;
                    ShopCarNet.getShopCarNet().updateProductNum(String.valueOf(newNum),itemData,position);
                }
            }
        });
    }

    private void editConvert(final ShopcarBean.ResultBean itemData, BaseViewHolder baseViewHolder, final int position) {
        if (CacheManager.getInstance().isAllSelectInEditMode()){
            checkBox.setChecked(true);
        }else if (CacheManager.getInstance().getDeleteShopCarBeanList().size()==0){
            checkBox.setChecked(false);
        }

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CacheManager.getInstance().adddeleteshopcarBean(itemData,position);
            }
        });
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
