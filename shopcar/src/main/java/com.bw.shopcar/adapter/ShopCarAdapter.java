package com.bw.shopcar.adapter;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bw.framework.BaseAdapter;
import com.bw.framework.CacheManager;
import com.bw.net.Contants;
import com.bw.net.bean.ShopCarBean;
import com.bw.shopcar.presenter.ShopCarPresenter;
import com.shopmall.bawei.shopcar.R;

public class ShopCarAdapter extends BaseAdapter<ShopCarBean> {

    private CheckBox cbGov;
    private ImageView ivGov;
    private TextView tvDescGov;
    private TextView tvPriceGov;
    private ImageButton addProductBtn;
    private TextView productNum;
    private ImageButton subProductBtn;


    private ShopCarPresenter shopcarPresenter;
    private boolean isEditMode;

    public void setShopcarPresenter(ShopCarPresenter shopcarPresenter) {
        this.shopcarPresenter = shopcarPresenter;
    }

    public void setEditMode(boolean isEditMode) {
        this.isEditMode = isEditMode;
        notifyDataSetChanged();//刷新列表
    }
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_shopcar;
    }

    @Override
    protected void convert(BaseViewHoder baseViewHoder, int viewType, ShopCarBean shopCarBean) {

        cbGov = (CheckBox) baseViewHoder.itemView.findViewById(R.id.cb_gov);
//        ivGov = (ImageView) baseViewHoder.itemView.findViewById(R.id.iv_gov);
        ivGov = baseViewHoder.getView(R.id.iv_gov);
        tvDescGov = (TextView) baseViewHoder.itemView.findViewById(R.id.tv_desc_gov);
        tvPriceGov = (TextView) baseViewHoder.itemView.findViewById(R.id.tv_price_gov);
        addProductBtn = (ImageButton) baseViewHoder.itemView.findViewById(R.id.addProductBtn);
        productNum = (TextView) baseViewHoder.itemView.findViewById(R.id.productNum);
        subProductBtn = (ImageButton) baseViewHoder.itemView.findViewById(R.id.subProductBtn);


        tvPriceGov.setText(shopCarBean.getProductPrice());
        tvDescGov.setText(shopCarBean.getProductName());
        Glide.with(baseViewHoder.itemView.getContext()).load(Contants.BASE_URl_IMAGE+shopCarBean.getUrl()).into(ivGov);

        productNum.setText(shopCarBean.getProductNum());

        if (!isEditMode) {//正常模式
            cbGov.setChecked(shopCarBean.isProductSelected());
            initProductSelectCheckBoxClickListener(cbGov, shopCarBean, viewType);
            initAddDecClickListener(baseViewHoder, shopCarBean, viewType);
        } else {//编辑模式
            initEditModeCheckBoxClickListener(cbGov, shopCarBean, viewType);
            if (CacheManager.getInstance().checkIfDataInDeleteShopcarBeanList(shopCarBean)) {
                cbGov.setChecked(true);
            } else {
                cbGov.setChecked(false);
            }
        }


    }

    private void initEditModeCheckBoxClickListener(final CheckBox produectSelectCheckBox, final ShopCarBean itemData, final int position) {
        produectSelectCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (produectSelectCheckBox.isChecked()) {
                    //当前在编辑模式下，选择了该商品，需要把该商品添加到删除队列中
                    CacheManager.getInstance().addDeleteShopCarBean(itemData, position);
                } else {
                    //在编辑模式下，该商品由已选择变为未选择，那么需要把它从删除队列中删除
                    CacheManager.getInstance().addDeleteShopCarBean(itemData, position);
                }
            }
        });
    }

    private void initAddDecClickListener(BaseViewHoder baseViewHolder, final ShopCarBean itemData, final int position) {
        baseViewHolder.getView(R.id.addProductBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //先获取之前产品的数量
                int oldNum = Integer.parseInt(itemData.getProductNum());
                int newNum = oldNum+1;
                shopcarPresenter.updateProductNum(itemData.getProductId(), String.valueOf(newNum), itemData.getProductName(), itemData.getUrl(),(String) itemData.getProductPrice(), position, String.valueOf(newNum));
            }
        });

        baseViewHolder.getView(R.id.subProductBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //先获取之前产品的数量
                int oldNum = Integer.parseInt(itemData.getProductNum());
                if (oldNum > 1) {//确保购物车上的商品数量大于或者等于1
                    int newNum = oldNum - 1;
                    shopcarPresenter.updateProductNum(itemData.getProductId(), String.valueOf(newNum), itemData.getProductName(), itemData.getUrl(), (String) itemData.getProductPrice(),position, String.valueOf(newNum));
                }
            }
        });
    }

    private void initProductSelectCheckBoxClickListener(final CheckBox produectSelectCheckBox, final ShopCarBean itemData, final int position) {
        produectSelectCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("---", isChecked+"");
                shopcarPresenter.updateProductSelected(itemData.getProductId(),isChecked,itemData.getProductName(),itemData.getUrl(),(String)itemData.getProductPrice(), position);

            }
        });
    }


    @Override
    public int getViewType(int position) {
        return 0;
    }
}
