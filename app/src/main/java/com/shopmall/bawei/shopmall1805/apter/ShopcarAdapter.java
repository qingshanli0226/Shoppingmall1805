package com.shopmall.bawei.shopmall1805.apter;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.deom.CacheManager;
import com.bawei.deom.selectedordelete.ShopcarPresenterImpl;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.apter.apter2.BaseRVAdapter;

import java.util.List;

import bean.Shoppingcartproducts;

public class ShopcarAdapter extends BaseRVAdapter<Shoppingcartproducts.ResultBean>  {
    private boolean isEditMode;//是否是编辑模式
   private ShopcarPresenterImpl shopcarPresenter;

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.iteam_shopcar;
    }
    public  void setEditMode(boolean isEditMode){
        this.isEditMode=isEditMode;
        notifyDataSetChanged();
    }
    public void setShopcarPresenter(ShopcarPresenterImpl shopcarPresenter) {
        this.shopcarPresenter = shopcarPresenter;
    }
    @Override
    protected void convert(Shoppingcartproducts.ResultBean itemData, BaseViewHolder baseViewHolder, int position) {

        TextView productName = baseViewHolder.getView(R.id.productName);
        TextView productPrice = baseViewHolder.getView(R.id.productPrices);
        TextView productCount = baseViewHolder.getView(R.id.productNums);

        productName.setText(""+itemData.getProductName());
        productPrice.setText(""+itemData.getProductPrice());
        productCount.setText(itemData.getProductNum()+"");
        ImageView imageView = baseViewHolder.getView(R.id.productImage);
        Glide.with(baseViewHolder.itemView.getContext()).load(itemData.getUrl()).into(imageView);
        final CheckBox produectSelectCheckBox=baseViewHolder.getView(R.id.productSelect);
        if (!isEditMode){//正常模式
            produectSelectCheckBox.setChecked(itemData.isProductSelected());//如果在正常就更具服务端接口中的状态进行状态切换
            initProductSelectCheckBoxClickListener(produectSelectCheckBox,itemData,position);//更新服务端数据将你选中的状态那条数据源和下标
            initAddDecClickListener(baseViewHolder, itemData, position);//点击添加

        }else {
            initAddDecClickListener(baseViewHolder, itemData, position);//点击添加
            initEditModeCheckBoxClickListener(produectSelectCheckBox,itemData,position);
              if (CacheManager.getInstance().checkIfDataInDeleteShopcarBeanList(itemData)){
                  produectSelectCheckBox.setChecked(true);
              }else {
                  produectSelectCheckBox.setChecked(false);
              }
        }
    }




    private void initAddDecClickListener(BaseViewHolder baseViewHolder, final Shoppingcartproducts.ResultBean itemData, final int position) {
     baseViewHolder.getView(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

                  int  oldNum=Integer.parseInt(itemData.getProductNum());//点击添加

                 int newNUm=oldNum+1;//每次在原来的数据上加一
                 shopcarPresenter.updateProductNum(itemData.getProductId(),String.valueOf(newNUm),itemData.getProductName(),itemData.getUrl(),itemData.getProductPrice(),position,String.valueOf(newNUm));
                // 调用服务端接口刷新数据源把现有的数据刷新服务端

         }
     });
          baseViewHolder.getView(R.id.btnSub).setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  int  oldNum=Integer.parseInt(itemData.getProductNum());
            if (oldNum>=1){
                  int newNUm=oldNum-1;
                  shopcarPresenter.updateProductNum(itemData.getProductId(),String.valueOf(newNUm),itemData.getProductName(),itemData.getUrl(),itemData.getProductPrice(),position,String.valueOf(newNUm));
              }
              }
          });
    }

    private void initProductSelectCheckBoxClickListener(final CheckBox produectSelectCheckBox, final Shoppingcartproducts.ResultBean itemData, final int position) {
        produectSelectCheckBox.setOnClickListener(new View.OnClickListener() {//被点击以后
            @Override
            public void onClick(View v) {
//                Log.d("LQS", isChecked+"");
                shopcarPresenter.updateProductSelected(itemData.getProductId(),true,itemData.getProductName(),itemData.getUrl(),(String)itemData.getProductPrice(), position);
                //调用服务端更新接口的数据源

            }
        });

    }
    private void initEditModeCheckBoxClickListener(final CheckBox produectSelectCheckBox, final Shoppingcartproducts.ResultBean itemData, final int position) {
       produectSelectCheckBox.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (produectSelectCheckBox.isChecked()){
                   //当前在编辑模式下，选择了该商品，需要改商品添加到删除队列
                   CacheManager.getInstance().addDeleteShopcarBean(itemData,position);
               }else {
                   //在编辑模式下，该商品由已经选择变为未选择，那么需要把它从删除队列中删除
                   CacheManager.getInstance().deleteOneShopcarBean(itemData,position);
               }
           }
       });
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }


}
