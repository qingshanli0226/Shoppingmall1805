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

import java.util.List;

public class ShopCarAdapter extends BaseAdapter<ShopCarBean> implements CacheManager.IShopcarDataChangeListener{

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
    protected void convert(BaseViewHoder baseViewHoder, int position, ShopCarBean shopCarBean) {

        cbGov = (CheckBox) baseViewHoder.getView(R.id.cb_gov);
        ivGov = baseViewHoder.getView(R.id.iv_gov);
        tvDescGov = (TextView) baseViewHoder.getView(R.id.tv_desc_gov);
        tvPriceGov = (TextView) baseViewHoder.getView(R.id.tv_price_gov);
        productNum = (TextView) baseViewHoder.getView(R.id.productNum);
        addProductBtn = baseViewHoder.getView(R.id.addProductBtn);
        subProductBtn = baseViewHoder.getView(R.id.subProductBtn);

        tvPriceGov.setText(shopCarBean.getProductPrice());
        tvDescGov.setText(shopCarBean.getProductName());
        Glide.with(baseViewHoder.itemView.getContext()).load(Contants.BASE_URl_IMAGE+shopCarBean.getUrl()).into(ivGov);

        productNum.setText(shopCarBean.getProductNum());

        if (!isEditMode) {//正常模式
            cbGov.setChecked(shopCarBean.isProductSelected());
            initSelectCheckBoxClickListener(cbGov, shopCarBean, position);
            initClickListener(baseViewHoder, shopCarBean, position);
        } else {//编辑模式
            initEditCheckBoxClickListener(cbGov, shopCarBean, position);
            if (CacheManager.getInstance().checkIfDataInDeleteShopcarBeanList(shopCarBean)) {
                cbGov.setChecked(true);
            } else {
                cbGov.setChecked(false);
            }
        }


    }

    private void initEditCheckBoxClickListener(final CheckBox produectSelectCheckBox, final ShopCarBean shopCarBean, final int position) {
        produectSelectCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (produectSelectCheckBox.isChecked()) {
                    //当前在编辑模式下，选择了该商品，需要把该商品添加到删除队列中
                    CacheManager.getInstance().addDeleteShopCarBean(shopCarBean, position);
                } else {
                    //在编辑模式下，该商品由已选择变为未选择，那么需要把它从删除队列中删除
                    CacheManager.getInstance().addDeleteShopCarBean(shopCarBean, position);
                }
            }
        });
    }

    private void initClickListener(BaseViewHoder baseViewHolder, final ShopCarBean shopCarBean, final int position) {
        addProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("---", "onClick: addProduct"+position+",,,"+shopCarBean.getProductName());

                //先获取之前产品的数量
                int oldNum = Integer.parseInt(shopCarBean.getProductNum());
                int newNum = oldNum + 1;
                shopcarPresenter.updateProductNum(shopCarBean.getProductId(), String.valueOf(newNum), shopCarBean.getProductName(), shopCarBean.getUrl(),(String) shopCarBean.getProductPrice(), position, String.valueOf(newNum));
            }
        });

        subProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("---", "onClick: subProduct"+position+",,,"+shopCarBean.getProductName());

                //先获取之前产品的数量
                int oldNum = Integer.parseInt(shopCarBean.getProductNum());
                if (oldNum > 1) {//确保购物车上的商品数量大于或者等于1
                    int newNum = oldNum - 1;
                    shopcarPresenter.updateProductNum(shopCarBean.getProductId(), String.valueOf(newNum), shopCarBean.getProductName(), shopCarBean.getUrl(), (String) shopCarBean.getProductPrice(),position, String.valueOf(newNum));
                }
            }
        });
    }

    private void initSelectCheckBoxClickListener(final CheckBox produectSelectCheckBox, final ShopCarBean shopCarBean, final int position) {
        produectSelectCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (produectSelectCheckBox.isChecked()){
                    shopcarPresenter.updateProductSelected(shopCarBean.getProductId(),true,shopCarBean.getProductName(),shopCarBean.getUrl(),shopCarBean.getProductPrice(), position);
                }else {
                    shopcarPresenter.updateProductSelected(shopCarBean.getProductId(),false,shopCarBean.getProductName(),shopCarBean.getUrl(),shopCarBean.getProductPrice(), position);
                }

            }
        });

//        produectSelectCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                shopcarPresenter.updateProductSelected(shopCarBean.getProductId(),isChecked,shopCarBean.getProductName(),shopCarBean.getUrl(),(String)shopCarBean.getProductPrice(), position);
//            }
//        });
    }


    @Override
    public int getViewType(int position) {
        return position;
    }

    @Override
    public void onDataChanged(List<ShopCarBean> shopCarBeanList) {

    }

    @Override
    public void onOneDataChanged(int position, ShopCarBean shopCarBean) {

    }

    @Override
    public void onMoneyChanged(String moneyValue) {
    }

    @Override
    public void onAllSelected(boolean isAllSelect) {

    }
}
