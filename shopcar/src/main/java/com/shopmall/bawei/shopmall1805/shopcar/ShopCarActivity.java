package com.shopmall.bawei.shopmall1805.shopcar;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.MessageQueue;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
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
    private int newPosition;
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
                    CacheManager.getInstance().selectCompileAll(true);
                }else {
                    CacheManager.getInstance().selectCompileAll(false );
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
        newPosition = position;
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
        Toast.makeText(this, ""+result, Toast.LENGTH_SHORT).show();
        CacheManager.getInstance().processDeleteProducts();
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.shopcar_compile_delete_bt) {
            List<ShopcarBean> deleteShopcarBeanList = CacheManager.getInstance().getDeleteShopcarBeanList();
            if (deleteShopcarBeanList.size()>0) {
                httpPresenter.deleteProducts(deleteShopcarBeanList);
            } else {
                showMessage("删除失败");
            }
        } else if (view.getId() == R.id.bt_jiesuan) {
            List<ShopcarBean> shopcarBeanList = CacheManager.getInstance().getShopcarBeanList();
            String names = shopcarBeanList.get(newPosition).getProductName();
            String url = shopcarBeanList.get(newPosition).getUrl();
            String productNum = shopcarBeanList.get(newPosition).getProductNum();
            String allMoney = CacheManager.getInstance().getMoneyValue();

            SharedPreferences address = getSharedPreferences("address", MODE_PRIVATE);
            boolean loca = address.getBoolean("loca", false);
            if(loca){
                Log.i("TAG", "onClick: "+names);
                ARouter.getInstance().build("/pay/PayActivity")
                        .withString("url",url)
                        .withString("names",names)
                        .withString("productNum",productNum)
                        .withString("allMoney",allMoney)
                        .navigation();
            }else {
                dialog();
            }
        }
    }
    private void dialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("提示");
        builder.setMessage("是否去填写地址");
        View inflate = getLayoutInflater().inflate(R.layout.view_dialog, null);
        builder.setView(inflate);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(ShopCarActivity.this,AddAddressActivity.class));
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).show();
    }
}