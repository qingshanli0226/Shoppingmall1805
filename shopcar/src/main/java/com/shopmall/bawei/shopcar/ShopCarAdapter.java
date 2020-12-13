package com.shopmall.bawei.shopcar;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.framework.CacheManager;
import com.example.net.ConfigUrl;
import com.example.net.ShopcarBean;
import com.shopmall.bawei.shopcar.presenter.ShopcarPresenterImpl;

import java.util.List;

public class ShopCarAdapter extends BaseQuickAdapter<ShopcarBean, BaseViewHolder> {

    private ShopcarPresenterImpl shopcarPresenter;
    private boolean isEditMode;

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

        ImageView addNum = helper.getView(R.id.btn_add);
        ImageView subNum = helper.getView(R.id.btn_sub);





        CheckBox view = helper.getView(R.id.cb_gov);
        helper.addOnClickListener(R.id.cb_gov);
        view.setChecked(item.isProductSelected());
        if (!isEditMode){
            view.setChecked(item.isProductSelected());
            initProductSelectCheckBoxClickListener(helper,view, item);
            initAddDecClickListener(helper,addNum,subNum,item);
        }else {
            initEditModeCheckBoxClickListener(helper,view,item);
            if (CacheManager.getInstance().checkIfDataInDeleteShopcarBeanList(item)){
                view.setChecked(true);
            }else {
                view.setChecked(false);
            }
        }


    }

    private void initEditModeCheckBoxClickListener(final BaseViewHolder helper, final CheckBox view, final ShopcarBean item) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (view.isChecked()) {
                    //当前在编辑模式下，选择了该商品，需要把该商品添加到删除队列中
                    CacheManager.getInstance().addDeleteShopcarBean(item, helper.getPosition());
                } else {
                    //在编辑模式下，该商品由已选择变为未选择，那么需要把它从删除队列中删除
                    CacheManager.getInstance().deleteOneShopcarBean(item, helper.getPosition());
                }
            }
        });
    }

    private void initAddDecClickListener(final BaseViewHolder helper, ImageView addNum, ImageView subNum, final ShopcarBean item) {
        addNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "111", Toast.LENGTH_SHORT).show();
                int oldNum = Integer.parseInt(item.getProductNum());
                int newNum = oldNum+1;
                shopcarPresenter.updateProductNum(item.getProductId(),String.valueOf(newNum),item.getProductName(),item.getUrl(), (String) item.getProductPrice(),helper.getPosition(),String.valueOf(newNum));

            }
        });

        subNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int oldNum = Integer.parseInt(item.getProductNum());
                if (oldNum > 1) {//确保购物车上的商品数量大于或者等于1
                    int newNum = oldNum - 1;
                    shopcarPresenter.updateProductNum(item.getProductId(), String.valueOf(newNum), item.getProductName(), item.getUrl(), (String) item.getProductPrice(),helper.getPosition(), String.valueOf(newNum));
                }
            }
        });
    }

    private void initProductSelectCheckBoxClickListener(final BaseViewHolder helper, CheckBox view, final ShopcarBean item) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "这是单选框", Toast.LENGTH_SHORT).show();
                shopcarPresenter.updateProductSelected(item.getProductId(),view.isSelected(),item.getProductName(),item.getUrl(), (String) item.getProductPrice(),helper.getPosition());
            }
        });
    }
}
