package com.shopmall.bawei.shopmall1805.shopcar.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.shopmall.bawei.shopcar.R;
import com.shopmall.bawei.shopmall1805.common.ConfigUrl;
import com.shopmall.bawei.shopmall1805.framework.BaseRvAdapter;
import com.shopmall.bawei.shopmall1805.framework.service.CacheManager;
import com.shopmall.bawei.shopmall1805.net.entity.ShopcarBean;
import com.shopmall.bawei.shopmall1805.shopcar.presenter.ShopcarPresenterImpl;

public class ShopCarAdapter extends BaseRvAdapter<ShopcarBean> {

    private boolean isEditModel; //是否编辑模式
    private ShopcarPresenterImpl shopcarPresenter;

    public void setShopcarPresenter(ShopcarPresenterImpl shopcarPresenter) {
        this.shopcarPresenter = shopcarPresenter;
    }

    public void setEditModel(boolean isEditModel){
        this.isEditModel = isEditModel;
        notifyDataSetChanged();
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_shopcar;
    }
    @Override
    public void convert(BaseViewHolder holder, int position, ShopcarBean shopcarBean) {
        Glide.with(holder.itemView.getContext()).load(ConfigUrl.BASE_IMAGE+shopcarBean.getUrl()).into((ImageView) holder.getView(R.id.item_shopcar_img));
        TextView name = holder.getView(R.id.item_shopcar_title_tv);
        name.setText(shopcarBean.getProductName());
        TextView price = holder.getView(R.id.item_shopcar_price_tv);
        price.setText(shopcarBean.getProductPrice());
        TextView number = holder.getView(R.id.item_shopcar_number_tv);
        number.setText(shopcarBean.getProductNum());

        CheckBox checkBox = holder.getView(R.id.item_shopcar_cb);

        if(!isEditModel){
            checkBox.setChecked(shopcarBean.isProductSelected());
            initRadioButtonListener(checkBox,shopcarBean,position);
            initAddDecClickListener(holder,shopcarBean,position);
        }else {
            initEditModeCheckBoxClickListener(checkBox,shopcarBean,position);
            if(CacheManager.getInstance().checkIfDataInDeleteShopcarBeanList(shopcarBean)){
                checkBox.setChecked(true);
            }else {
                checkBox.setChecked(false);
            }
        }
    }

    private void initEditModeCheckBoxClickListener(final CheckBox checkBox, final ShopcarBean shopcarBean, final int position) {
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkBox.isChecked()){
                    CacheManager.getInstance().addDeleteQueue(shopcarBean,position);
                }else {
                    CacheManager.getInstance().deleteShopcarQueue(shopcarBean,position);
                }
            }
        });
    }
    private void initAddDecClickListener(BaseViewHolder holder, final ShopcarBean shopcarBean, final int position) {
        holder.getView(R.id.item_shopcar_jia_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int oldNumber = Integer.parseInt(shopcarBean.getProductNum());
                int newNumber = oldNumber+1;
                shopcarPresenter.upNumberChanger(shopcarBean.getProductId(),shopcarBean.getProductNum(),shopcarBean.getProductName(),shopcarBean.getUrl(),shopcarBean.getProductPrice(),position,String.valueOf(newNumber));
            }
        });
        holder.getView(R.id.item_shopcar_jian_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int oldNumber = Integer.parseInt(shopcarBean.getProductNum());
                if(oldNumber > 1){
                    int newNumber = oldNumber-1;
                    shopcarPresenter.upNumberChanger(shopcarBean.getProductId(),shopcarBean.getProductNum(),shopcarBean.getProductName(),shopcarBean.getUrl(),shopcarBean.getProductPrice(),position,String.valueOf(newNumber));
                }
            }
        });
    }
    private void initRadioButtonListener(final CheckBox checkBox, final ShopcarBean shopcarBean, final int position) {
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shopcarPresenter.upDateSelect(shopcarBean.getProductId(),checkBox.isChecked(), shopcarBean.getProductName(), shopcarBean.getUrl(), shopcarBean.getProductPrice(), position);
            }
        });
    }
    @Override
    public int getViewType(int position) {
        return 0;
    }
}
