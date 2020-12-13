package com.shopmall.bawei.shopmall1805.shopcar;


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shopmall.bawei.shopcar.R;
import com.shopmall.bawei.shopmall1805.common.ShopmallConstant;
import com.shopmall.bawei.shopmall1805.framework.BaseMVPActivity;
import com.shopmall.bawei.shopmall1805.framework.service.CacheManager;
import com.shopmall.bawei.shopmall1805.framework.view.Toolbar;
import com.shopmall.bawei.shopmall1805.net.entity.ShopcarBean;
import com.shopmall.bawei.shopmall1805.shopcar.adapter.ShopCarAdapter;
import com.shopmall.bawei.shopmall1805.shopcar.contract.ShopcarContract;
import com.shopmall.bawei.shopmall1805.shopcar.presenter.ShopcarPresenterImpl;

import java.util.ArrayList;
import java.util.List;


@Route(path = ShopmallConstant.SHOP_CAR_ACTIVITY_PATH)
public class ShopCarActivity extends BaseMVPActivity<ShopcarPresenterImpl, ShopcarContract.IShopcarView> implements ShopcarContract.IShopcarView, View.OnClickListener {
    private Toolbar toolbar;
    private RecyclerView shopcarRv;
    private RelativeLayout shaopcar;
    private RelativeLayout shaopcarCompileRl;
    private CheckBox shopcarCompileCheckAll;
    private Button shopcarCompileCollectBt;
    private Button shopcarCompileDeleteBt;
    private RelativeLayout shaopcarAccomplishRl;
    private CheckBox shopcarAccomplishCheckAll;
    private TextView shopcarAccomplishPrice;
    private boolean isCutState;
    private boolean isAllCheckState = true;
    private List<ShopcarBean> list=new ArrayList<>();
    private ShopCarAdapter shopCarAdapter;
    private int shopCarNumber = 1;
    private boolean flag=false;
    private boolean newAllSelect;
    private Button btJiesuan;
    @Override
    protected void initPresenter() {
        httpPresenter = new ShopcarPresenterImpl();
        shopCarAdapter.setShopcarPresenter(httpPresenter);
    }

    private CacheManager.IShopcarDataChangeListener iShopcarDataChangeListener=new CacheManager.IShopcarDataChangeListener() {
        @Override
        public void onDataChanged(List<ShopcarBean> shopcarBeanList) {
            shopCarAdapter.upDataText(shopcarBeanList);
        }
        @Override
        public void onOneDataChanged(int position, ShopcarBean shopcarBean) {
            shopCarAdapter.notifyItemChanged(position);
        }
        @Override
        public void onMoneyChanged(String moneyValue) {
            shopcarAccomplishPrice.setText("￥"+moneyValue);
        }
        @Override
        public void onAllSelected(boolean isAllSelect) {
            if(flag){
                shopcarCompileCheckAll.setChecked(true);
            }else {
                shopcarAccomplishCheckAll.setChecked(false);
            }
        }
    };

    @Override
    protected void initData() {
        toolbar.setToolBarTitle("购物车");
        List<ShopcarBean> shopcarBeanList = CacheManager.getInstance().getShopcarBeanList();
        shopCarAdapter.upDataText(shopcarBeanList);
        shopcarAccomplishPrice.setText("￥"+CacheManager.getInstance().getMoneyValue());

        CacheManager.getInstance().setShopcarDataChanger(iShopcarDataChangeListener);
        if(CacheManager.getInstance().isAccomplish()){
            shopcarAccomplishCheckAll.setChecked(true);
        }else {
            shopcarAccomplishCheckAll.setChecked(false);
        }

        shopcarAccomplishCheckAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(shopcarAccomplishCheckAll.isChecked()){
                    isCutState = true;
                }else {
                    isCutState = false;
                }
                httpPresenter.upSelectAll(isCutState);
            }
        });
        shopcarCompileCheckAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(shopcarCompileCheckAll.isChecked()){
                    CacheManager.getInstance().selectCompileAll(false);
                }else {
                    CacheManager.getInstance().selectCompileAll(true );
                }
            }
        });
    }
    @Override
    public void onRightClick() {
        super.onRightClick();
        if(!flag){
            flag=true;
            toolbar.setToolbarRightTv("完成");
            shopCarAdapter.setEditModel(flag);
            shaopcarAccomplishRl.setVisibility(View.GONE);
            shaopcarCompileRl.setVisibility(View.VISIBLE);
            if(CacheManager.getInstance().isAllSelectInEditMode()){
                shopcarCompileCheckAll.setChecked(true);
            }
        }else {
            flag = false;
            toolbar.setToolbarRightTv("编辑");
            shopCarAdapter.setEditModel(flag);
            shaopcarAccomplishRl.setVisibility(View.VISIBLE);
            shaopcarCompileRl.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initView() {
        toolbar = findViewById(R.id.toolbar);
        shopcarRv = findViewById(R.id.shopcar_rv);
        shaopcar = findViewById(R.id.shaopcar);
        shaopcarCompileRl= findViewById(R.id.shaopcar_compile_rl);
        shopcarCompileCheckAll = findViewById(R.id.shopcar_compile_check_all);
        shopcarCompileCollectBt = findViewById(R.id.shopcar_compile_collect_bt);
        shopcarCompileDeleteBt = findViewById(R.id.shopcar_compile_delete_bt);
        shaopcarAccomplishRl = findViewById(R.id.shaopcar_accomplish_rl);
        shopcarAccomplishCheckAll = findViewById(R.id.shopcar_accomplish_check_all);
        shopcarAccomplishPrice = findViewById(R.id.shopcar_accomplish_price);
        shopcarCompileCollectBt.setOnClickListener(this);
        shopcarCompileDeleteBt.setOnClickListener(this);
        btJiesuan = findViewById(R.id.bt_jiesuan);
        btJiesuan.setOnClickListener(this);
        toolbar.setToolbarRightTv("编辑");
        shopcarRv.setLayoutManager(new LinearLayoutManager(this));
        shopCarAdapter=new ShopCarAdapter();
        shopcarRv.setAdapter(shopCarAdapter);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_shopcar;
    }
    @Override
    public void showLoaing() {
    }
    @Override
    public void hideLoading() {
    }
    @Override
    public void showEmpty() {
    }
    @Override
    public void onUpdateSelect(String result, int position) {
        showMessage("购物车改变");
        CacheManager.getInstance().updateProductSelected(position);
    }
    @Override
    public void onNumberChanger(String result, int position, String newNumber) {
        Toast.makeText(this, "商品修改成功", Toast.LENGTH_SHORT).show();
        CacheManager.getInstance().updateProductNum(position, newNumber);
    }
    @Override
    public void onAllSelect(String result) {
        CacheManager.getInstance().selectAllProduct(newAllSelect);
    }
    @Override
    public void onDeleteProducts(String result) {

    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.shopcar_compile_delete_bt) {
            List<ShopcarBean> deleteShopcarBeanList = CacheManager.getInstance().getDeleteShopcarBeanList();
            if (deleteShopcarBeanList.size()>0) {
                httpPresenter.deleteProducts(deleteShopcarBeanList);
            } else {
                showMessage("当前没有要删除的数据");
            }
        } else if (view.getId() == R.id.bt_jiesuan) {
            //第一步检查购物车上商品在仓库中是否都有库存
            Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
        }
    }
}