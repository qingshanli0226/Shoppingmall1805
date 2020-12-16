package com.shopmall.bawei.shopcar.view;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.common.ARouterHelper;
import com.shopmall.bawei.framework.BaseActivity;
import com.shopmall.bawei.framework.CacheManager;
import com.shopmall.bawei.framework.UserManager;
import com.shopmall.bawei.framework.view.MyToolBar;
import com.shopmall.bawei.net.mode.InventoryBean;
import com.shopmall.bawei.net.mode.OrderInfoBean;
import com.shopmall.bawei.net.mode.ShopCarBean;
import com.shopmall.bawei.shopcar.BindActivity;
import com.shopmall.bawei.shopcar.R;
import com.shopmall.bawei.shopcar.adapter.ShopCarAdapter;
import com.shopmall.bawei.shopcar.contract.ShopCarContract;
import com.shopmall.bawei.shopcar.contract.ShopCarPresenterImpl;

import java.util.ArrayList;
import java.util.List;

@Route(path = ARouterHelper.SHOP_CAR)
public class ShopCarActivity extends BaseActivity<ShopCarPresenterImpl, ShopCarContract.IShopCarView> implements ShopCarContract.IShopCarView, View.OnClickListener {

    private CheckBox checkboxAll;
    private Button btnCheckOut;
    private Button btnDelete;
    private TextView tvShopcartTotal;
    private RecyclerView recyclerview;


    private ShopCarAdapter adapter;


    private LinearLayout llCheckAll;
    private LinearLayout llDelete;
    private LinearLayout llEmptyShopcart;

    private CheckBox cbAll;
    private boolean isEditMode = false;//编辑模式？
    private boolean newAllSelect;

    private MyToolBar toolBar;


    private List<ShopCarBean> shopCarBeanList = new ArrayList<>();




    private CacheManager.IShopcarDataChangeListener iShopcarDataChangeListener = new CacheManager.IShopcarDataChangeListener(){

        @Override
        public void onDataChanged(List<ShopCarBean> shopcarBeanList) {
            adapter.updateData(shopcarBeanList);
            if(shopcarBeanList.size() != 0){
                llEmptyShopcart.setVisibility(View.GONE);
                toolBar.setRightVisible(true);
            } else {
                llEmptyShopcart.setVisibility(View.VISIBLE);
                toolBar.setRightVisible(false);
            }
        }

        @Override
        public void onOneDataChanged(final int position, ShopCarBean shopcarBean) {
            if(recyclerview.isComputingLayout()) {
                recyclerview.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyItemChanged(position);
                    }
                });
            } else {
                adapter.notifyItemChanged(position);
            }
        }

        @Override
        public void onMoneyChanged(String moneyValue) {
            tvShopcartTotal.setText("￥"+moneyValue);
        }

        @Override
        public void onAllSelected(boolean isAllSelect) {
            if(isEditMode){
                cbAll.setChecked(isAllSelect);
            }else{
                checkboxAll.setChecked(isAllSelect);
            }
        }
    };


    @Override
    protected int layoutId() {
        return R.layout.fragment_shoppingcart;
    }

    @Override
    protected void initView() {
        llEmptyShopcart = (LinearLayout) findViewById(R.id.ll_empty_shopcart);

        llCheckAll = (LinearLayout) findViewById(R.id.ll_check_all);
        llDelete = (LinearLayout) findViewById(R.id.ll_delete);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        toolBar = findViewById(R.id.toolbar);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ShopCarAdapter();/******/
        recyclerview.setAdapter(adapter);
        btnCheckOut = (Button) findViewById(R.id.btn_check_out);
        btnDelete = (Button) findViewById(R.id.btn_delete);
        tvShopcartTotal = (TextView) findViewById(R.id.tv_shopcart_total);
        checkboxAll = (CheckBox) findViewById(R.id.checkbox_all);
        cbAll = (CheckBox) findViewById(R.id.cb_all);
        toolBar.setToolBarClickListner(this);
    }

    @Override
    protected void initData() {
        shopCarBeanList = CacheManager.getInstance().getShopCarBeanList();
        adapter.updateData(shopCarBeanList);
        tvShopcartTotal.setText("￥"+CacheManager.getInstance().getMoneyValue());
        CacheManager.getInstance().setShopCarDataChangeListener(iShopcarDataChangeListener);
        if(CacheManager.getInstance().isAllSelected()){
            checkboxAll.setChecked(true);
        } else {
            checkboxAll.setChecked(false);
        }

        checkboxAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkboxAll.isChecked()) {
                    newAllSelect = true;
                } else {
                    newAllSelect = false;
                }
                httpPresenter.selectAllProduct(newAllSelect);
            }
        });
        cbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbAll.isChecked()){
                    CacheManager.getInstance().selectAllProductInEditMode(true);
                } else {
                    CacheManager.getInstance().selectAllProductInEditMode(false);
                }
            }
        });

        if (shopCarBeanList.size() == 0){
            llEmptyShopcart.setVisibility(View.VISIBLE);
            toolBar.setRightVisible(false);
        }
    }

    @Override
    protected void initListener() {
        btnCheckOut.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        toolBar.setToolBarClickListner(this);
    }

    @Override
    protected void initPresenter() {
        httpPresenter = new ShopCarPresenterImpl();
        adapter.setShopCarPresenter(httpPresenter);
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {
        if(!isEditMode){
            isEditMode = true;
            toolBar.setRightTvText("完成");
            adapter.setEditMode(isEditMode);
            llDelete.setVisibility(View.VISIBLE);
            llCheckAll.setVisibility(View.GONE);
            if(CacheManager.getInstance().isAllSelectInEditMode()){
                cbAll.setChecked(false);
            }
        } else {
            isEditMode = false;
            toolBar.setRightTvText("编辑");
            adapter.setEditMode(isEditMode);
            llDelete.setVisibility(View.GONE);
            llCheckAll.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CacheManager.getInstance().unSetShopCarDataChangeListener(iShopcarDataChangeListener);
    }

    @Override
    public void onProductNumChanges(String result, int position, String newNum) {
        CacheManager.getInstance().updateProductNum(position,newNum);
    }

    @Override
    public void onProductSelected(String result, int position) {
        CacheManager.getInstance().updateProductSelected(position);
    }

    @Override
    public void onAllSelected(String result) {
        CacheManager.getInstance().selectAllProduct(newAllSelect);

    }

    @Override
    public void onDeleteProducts(String result) {
        CacheManager.getInstance().processDeleteProducts();
    }

    @Override
    public void onInventory(List<InventoryBean> list) {

    }

    @Override
    public void onOrderInfo(OrderInfoBean orderInfoBean) {

    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void showLoaDing() {
        showLoading();
    }

    @Override
    public void showEmpty() {
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_delete){
            List<ShopCarBean> deleteShopcarBeanList = CacheManager.getInstance().getDeleteShopcarBeanList();
            if(deleteShopcarBeanList.size() > 0){
                httpPresenter.deleteProducts(deleteShopcarBeanList);
            } else {
                Toast.makeText(this, "没有要删除的数据", Toast.LENGTH_SHORT).show();
            }
        } else if(v.getId() == R.id.btn_check_out){
            /**
             *
             *
             *
             */
            if(UserManager.getInstance().isBindPhone() && UserManager.getInstance().isBindAddress()){
                ARouter.getInstance().build(ARouterHelper.ORDER_ACTIVITY).navigation();
            } else {
                startActivity(new Intent(this, BindActivity.class));
            }
        }
    }
}
