package view.adaper;


import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shopmall.bawei.shopcar.R;

import framework.BaseRvAdper;
import framework.CacheManagerc;
import mode.ShopcarBean;
import view.ShopmallConstant;
import view.presenter.ShopcarPresenterImplc;

public class ShopAdaper extends BaseRvAdper<ShopcarBean> {

    private boolean isEditMode;//是否是编辑

    private ShopcarPresenterImplc shopcarPresenter;

    public void setShopcarPresenter(ShopcarPresenterImplc shopcarPresenter){
        this.shopcarPresenter = shopcarPresenter;
    }
    public void setEditMode(boolean isEditMode){
        this.isEditMode = isEditMode;
        notifyDataSetChanged();
    }
    @Override
    protected int getlayourId(int i) {
        return R.layout.item_shop_car;
    }

    @Override
    protected void convert(ShopcarBean itemData, BaseviewHoder holder, int position) {
        Glide.with(holder.itemView.getContext()).load(ShopmallConstant.BASE_RESOURCE_IMAGE_URL+itemData.getUrl())
                .into((ImageView)holder.getView(R.id.productImage));
        TextView produceNameTv = holder.getView(R.id.productName);
        produceNameTv.setText(itemData.getProductName());
        TextView producePriceTv = holder.getView(R.id.productPrice);
        producePriceTv.setText(itemData.getProductPrice());
        TextView produceNumTv = holder.getView(R.id.productCount);
        produceNumTv.setText(itemData.getProductNum());

        CheckBox produectSelectCheckBox  = holder.getView(R.id.productSelect);
        if (isEditMode){//正常模式
            produectSelectCheckBox.setChecked(itemData.isProductSelected());
            initProDuctSeltctCheckBoxClickListener(produectSelectCheckBox,itemData,position);
            initAddDecClickListener(holder,itemData,position);
        }else {
            initEditModeCheckBoxClicklistener(produectSelectCheckBox,itemData,position);
            if (CacheManagerc.getInstance().checkIfDataInDeleteShopcarBeanList(itemData)){
                produectSelectCheckBox.setChecked(true);
            }else {
                produectSelectCheckBox.setChecked(false);
            }
        }
    }

    private void initEditModeCheckBoxClicklistener(final CheckBox produectSelectCheckBox, final ShopcarBean itemData, final int position) {
        produectSelectCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (produectSelectCheckBox.isChecked()){
                    //当在编辑模式下，选择了用该商品，须要吧该商品添加到删除队列中
                    CacheManagerc.getInstance().addDeleteShopcarBean(itemData,position);
                }else {
                    //在编辑模式下，该商品由已选择变成未选择，那么需要把它从删除队列中删除
                    CacheManagerc.getInstance().deleteOneShopcarBean(itemData,position);
                }
            }
        });
    }

    private void initAddDecClickListener(BaseviewHoder holder, final ShopcarBean itemData,final int position) {
            holder.getView(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //先获取之前产品的数量
                    int oldNum = Integer.parseInt(itemData.getProductNum());
                    int newNum = oldNum+1;
                    shopcarPresenter.updateProductNum(itemData.getProductId(), String.valueOf(newNum), itemData.getProductName(), itemData.getUrl(),(String) itemData.getProductPrice(), position, String.valueOf(newNum));
                }
            });

            holder.getView(R.id.btnSub).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //先获取之前产品的数量
                    int oldNum = Integer.parseInt(itemData.getProductNum());
                    if (oldNum>1){
                        int newNum = oldNum -1;
                        shopcarPresenter.updateProductNum(itemData.getProductId(), String.valueOf(newNum), itemData.getProductName(), itemData.getUrl(), (String) itemData.getProductPrice(),position, String.valueOf(newNum));
                    }
                }
            });
    }

    private void initProDuctSeltctCheckBoxClickListener(CheckBox produectSelectCheckBox, final ShopcarBean itemData, final int position) {
        produectSelectCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("LQS", isChecked+"");
                shopcarPresenter.updateProductSelected(itemData.getProductId(),isChecked,itemData.getProductName(),itemData.getUrl(),(String)itemData.getProductPrice(), position);

            }
        });
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
