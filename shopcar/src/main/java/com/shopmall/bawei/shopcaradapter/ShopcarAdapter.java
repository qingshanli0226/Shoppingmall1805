package com.shopmall.bawei.shopcaradapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.shopmall.bawei.framework.base.BaseRVAdapter;
import com.shopmall.bawei.framework.manager.ShopCarmanager;
import com.shopmall.bawei.framework.shopcar.ShopCarNet;
import com.shopmall.bawei.shopcar.R;
import com.shopmall.bean.ShopcarBean;
import com.shopmall.glide.Myglide;

public class ShopcarAdapter extends BaseRVAdapter<ShopcarBean.ResultBean> {
    private boolean isselect=false;

    public void setIsselect(boolean isselect){
          this.isselect=isselect;
          notifyDataSetChanged();
    }
    private ImageView viewById;
    private TextView name;
    private TextView money;
    private TextView num;
    private TextView jia;
    private TextView jian;
    private CheckBox checkBox;
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_shopcar;
    }

    @Override
    protected void convert(ShopcarBean.ResultBean itemData, final BaseViewHolder baseViewHolder, final int position) {
        viewById = baseViewHolder.itemView.findViewById(R.id.item_shopcar_image);
        name = baseViewHolder.itemView.findViewById(R.id.item_shopcar_name);
        money = baseViewHolder.itemView.findViewById(R.id.item_shopcar_money);
        num = baseViewHolder.itemView.findViewById(R.id.item_shopcar_num);
        jia = baseViewHolder.itemView.findViewById(R.id.item_shopcar_jia);
        jian = baseViewHolder.itemView.findViewById(R.id.item_shopcar_jian);
        checkBox = baseViewHolder.itemView.findViewById(R.id.item_shopcar_checkbox);

        name.setText(itemData.getProductName());
        money.setText("￥"+itemData.getProductPrice());
        num.setText(itemData.getProductNum());
        Myglide.getMyglide().centercenglide(baseViewHolder.itemView.getContext(),viewById,itemData.getUrl());
        //为true处于编辑状态，为false处于正常状态
        if (isselect){
             editconvert(itemData,baseViewHolder,position);
        }else {
             finshconvert(itemData,baseViewHolder,position);
        }


        
    }
    //为false,正常
    private void finshconvert(final ShopcarBean.ResultBean itemData, BaseViewHolder baseViewHolder, final int position) {
              ShopCarmanager.getShopCarmanager().delteclear();
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

    }

    //为true，编辑
    private void editconvert(final ShopcarBean.ResultBean itemData, final BaseViewHolder baseViewHolder, final int position) {
        if (ShopCarmanager.getShopCarmanager().isallselect()){
            checkBox.setChecked(true);
        }else if (ShopCarmanager.getShopCarmanager().getdeleteshopcarlist().size()==0){
            checkBox.setChecked(false);
        }

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopCarmanager.getShopCarmanager().adddeleteshopcarBean(itemData,position);
               // Toast.makeText(baseViewHolder.itemView.getContext(), ""+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
