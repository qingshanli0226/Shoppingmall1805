package com.shopmall.bawei.shopcar.adpter;

import android.media.Image;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.framework.BaseRVAdapter;
import com.example.framework.CacheManager;
import com.example.net.Confing;
import com.example.net.bean.ShopcarBean;
import com.shopmall.bawei.shopcar.R;
import com.shopmall.bawei.shopcar.presenter.ShopcarPresenterImpl;

public class ShopCarAdpter extends BaseRVAdapter<ShopcarBean> {

    private boolean isEditMode;//是否编辑模式
    private ShopcarPresenterImpl shopcarPresenter;

    public void setEditMode(boolean editMode) {
        isEditMode = editMode;
        notifyDataSetChanged();
    }

    public void setShopcarPresenter(ShopcarPresenterImpl shopcarPresenter) {
        this.shopcarPresenter = shopcarPresenter;

    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_shopcar;
    }

    @Override
    protected void convert(ShopcarBean itemData, BaseViewHolder baseViewHolder, int position) {
        ImageView iv_gov = baseViewHolder.getView(R.id.iv_gov);
        Glide.with(baseViewHolder.itemView.getContext()).load(Confing.BASE_IMAGE+itemData.getUrl()).into(iv_gov);
        TextView textname = baseViewHolder.getView(R.id.tv_desc_gov);
        textname.setText(""+itemData.getProductName());
        TextView textprice = baseViewHolder.getView(R.id.tv_price_gov);
        textprice.setText(""+itemData.getProductPrice());
        TextView textcount = baseViewHolder.getView(R.id.tv_count);
        textcount.setText(""+itemData.getProductNum());
        CheckBox checkBox = baseViewHolder.getView(R.id.cb_gov);

        if (!isEditMode){
            //正常模式下
            //商品的选择来自于服务端中
            checkBox.setChecked(itemData.isProductSelected());
            //更新服务端，将你现在的选择的状态存入到服务端中
            initProductSelectCheckBoxClicklister(checkBox,itemData,position);
            //点击数量增加、减少来更新服务端购物车的商品数量
            initaddDelteCheckListenter(baseViewHolder,itemData,position);
        }else {
            //编辑模式下
            //点击数量增加、减少来更新服务端购物车的商品数量
            initaddDelteCheckListenter(baseViewHolder,itemData,position);
            //编辑模式的时候，将你选择的商品存到要删除的集合当中
            initEditModeCheckBoxClickListenter(checkBox,itemData,position);
            if (CacheManager.getInstance().checkDeleteInfoShopCarBeanList(itemData)){
                checkBox.setChecked(true);
            }else {
                checkBox.setChecked(false);
            }
        }

    }

    private void initEditModeCheckBoxClickListenter(final CheckBox checkBox, final ShopcarBean itemData, final int position) {
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkBox.isChecked()){
                        //当前在编辑模式下，选择了该商品，就把该商品存到删除列表当中
                        CacheManager.getInstance().addDeleteShopBeans(itemData,position);
                    }else {
                        //在编辑模式下，全部选择变成了未选择，那么就要把商品从删除列表中删除
                        CacheManager.getInstance().deleteOneShopBeans(itemData,position);
                    }
                }
            });
    }

    private void initaddDelteCheckListenter(final BaseViewHolder baseViewHolder, final ShopcarBean itemData, final int position) {
        //点击增加数量
        baseViewHolder.getView(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //先获取之前的数量
                int oldNum = Integer.parseInt(itemData.getProductNum());
                //在原来的基础上把你的商品+1
                int newNum = oldNum + 1;
                //更新服务端商品的数量
                shopcarPresenter.updateProductChangeNum(itemData.getProductId(),String.valueOf(newNum),itemData.getProductName(),itemData.getUrl(),itemData.getProductPrice(),position,String.valueOf(newNum));

            }
        });
        //点击减少数量
        baseViewHolder.getView(R.id.btn_sub).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取之前的数量
                int oldNum = Integer.parseInt(itemData.getProductNum());
                //确保购物车数量大于等于一个
                if (oldNum>1){
                    //在原来的基础上把你的商品-1
                    int newNum = oldNum - 1;
                    //更新服务端商品的数量
                    shopcarPresenter.updateProductChangeNum(itemData.getProductId(),String.valueOf(newNum),itemData.getProductName(),itemData.getUrl(),itemData.getProductPrice(),position,String.valueOf(newNum));

                }
            }
        });
    }

    private void initProductSelectCheckBoxClicklister(final CheckBox checkBox, final ShopcarBean itemData, final int position) {
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("###",""+checkBox.isChecked());
                    //更新服务端商品的选择
                    shopcarPresenter.updateSelected(itemData.getProductId(),checkBox.isSelected(),itemData.getProductName(),itemData.getUrl(),itemData.getProductPrice(),position);


                }
            });

    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
