package view.adaper;


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.shopmall.bawei.shopcar.R;

import framework.BaseRvAdper;
import framework.CacheManagerc;
import mode.ShopcarBean;
import view.ShopmallConstant;
import view.spresenter.ShopcarPresenterImplc;

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
        TextView produceNameTv = holder.getView(R.id.productName);
        produceNameTv.setText(itemData.getProductName());
        TextView producePriceTv = holder.getView(R.id.productPrice);
        producePriceTv.setText(itemData.getProductPrice());
        TextView produceNumTv = holder.getView(R.id.productCount);
        produceNumTv.setText(itemData.getProductNum());

        //选择按钮
        CheckBox produectSelectCheckBox  = holder.getView(R.id.productSelect);
        if (!isEditMode){//正常模式
            produectSelectCheckBox.setChecked(itemData.getProductSelected());
            initProDuctSeltctCheckBoxClickListener(produectSelectCheckBox,itemData,position);
            initAddDecClickListener(holder,itemData,position);
        }else {//编辑模式下
            Log.i("pppp","编辑模式下"+itemData.toString());
            initEditModeCheckBoxClicklistener(produectSelectCheckBox,itemData,position);
            if (CacheManagerc.getInstance().checkIfDataInDeleteShopcarBeanList(itemData)){

                produectSelectCheckBox.setChecked(true);
            }else {
                produectSelectCheckBox.setChecked(false);
            }
        }
    }
    //编辑模式下程序
    private void initEditModeCheckBoxClicklistener(final CheckBox produectSelectCheckBox, final ShopcarBean itemData, final int position) {
        produectSelectCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (produectSelectCheckBox.isChecked()){
                    CacheManagerc.getInstance().addDeleteShopcarBean(itemData,position);
                }else {
                    CacheManagerc.getInstance().deleteOneShopcarBean(itemData,position);
                }
            }
        });
    }

    //正常模式下添加
    private void initAddDecClickListener(final BaseviewHoder holder, final ShopcarBean itemData, final int position) {
            holder.getView(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //先获取之前产品的数量
                    Toast.makeText(holder.itemView.getContext(), "+1", Toast.LENGTH_SHORT).show();
                    int oldNum = Integer.parseInt(itemData.getProductNum());
                    int newNum = oldNum+1;
                    Log.i("cccc","正常模式吓得添加"+newNum);
                    shopcarPresenter.updateProductNum(itemData.getProductId(), String.valueOf(newNum), itemData.getProductName(), itemData.getUrl(),(String) itemData.getProductPrice(), position, String.valueOf(newNum));
                }
            });

            holder.getView(R.id.btnSub).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //先获取之前产品的数量
                    int oldNum = Integer.parseInt(itemData.getProductNum());
                    Toast.makeText(holder.itemView.getContext(), "-1", Toast.LENGTH_SHORT).show();
                    if (oldNum>1){
                        int newNum = oldNum-1;
                        Log.i("cccc","正常模式吓得减小"+newNum);
                        shopcarPresenter.updateProductNum(itemData.getProductId(), String.valueOf(newNum), itemData.getProductName(), itemData.getUrl(), (String) itemData.getProductPrice(),position, String.valueOf(newNum));
                    }
                }
            });
    }
    boolean isshow = true;
    //正常模式下更新购物车产品的选择
    private void initProDuctSeltctCheckBoxClickListener(final CheckBox produectSelectCheckBox, final ShopcarBean itemData, final int position) {
        produectSelectCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(produectSelectCheckBox.getContext(), "111", Toast.LENGTH_SHORT).show();
                if (isshow){
                    isshow =false;
                    shopcarPresenter.updateProductSelected(itemData.getProductId(),isshow,itemData.getProductName(),itemData.getUrl(),(String)itemData.getProductPrice(), position);
                }else {
                    isshow = true;
                    shopcarPresenter.updateProductSelected(itemData.getProductId(),isshow,itemData.getProductName(),itemData.getUrl(),(String)itemData.getProductPrice(), position);
                }

            }
        });
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
