package com.shopmall.bawei.shopcar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.framework.BaseActivity;
import com.example.framework.CacheManager;
import com.example.net.ShopcarBean;
import com.shopmall.bawei.shopcar.contract.ShopcarContract;
import com.shopmall.bawei.shopcar.presenter.ShopcarPresenterImpl;

import java.util.ArrayList;
import java.util.List;

@Route(path = "/duo/shopcar")
public class MainActivity extends BaseActivity<ShopcarPresenterImpl, ShopcarContract.IShopcarView> implements ShopcarContract.IShopcarView,CacheManager.IShopcarDataChangeListener {

    private String path;
    private RecyclerView recyclerView;
    private List<ShopEntity> list = new ArrayList<>();
    private TextView bianji;
    private ShopCarAdapter shopCarAdapter;
    private boolean i;
    private LinearLayout linearLayout,linearLayout1;
    private CheckBox checkBox_all;
    private boolean newAllSelect;
    private  List<ShopcarBean> shopcarBeanList;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_shopcar;
    }

    @Override
    protected void initView() {
        CacheManager.getInstance().setShopcarDataChangeListener(this);
        recyclerView = findViewById(R.id.recyclerview);
        bianji = findViewById(R.id.tv_shopcart_edit);
        linearLayout = findViewById(R.id.ll_delete);
        linearLayout1 = findViewById(R.id.ll_check_all);
        checkBox_all = findViewById(R.id.checkbox_all);
    }

    @Override
    protected void initData() {
        ARouter.getInstance().inject(this);

        httpPresenter = new ShopcarPresenterImpl();

        bianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i){
                    i = false;
                    linearLayout1.setVisibility(View.VISIBLE);
                    linearLayout.setVisibility(View.GONE);

                }else {
                    i = true;
                    linearLayout1.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        shopcarBeanList = CacheManager.getInstance().getShopcarBeanList();

        shopCarAdapter = new ShopCarAdapter(R.layout.item_shopcar,shopcarBeanList);
        recyclerView.setAdapter(shopCarAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        shopCarAdapter.setShopcarPresenter(httpPresenter);



        if (CacheManager.getInstance().isAllSelected()){
            checkBox_all.setChecked(true);
        }else {
            checkBox_all.setChecked(false);
        }

        checkBox_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox_all.isChecked()){
                    //如果为true
                    newAllSelect = true;
                    httpPresenter.selectAllProduct(newAllSelect);
                }else {
                    newAllSelect = false;
                    httpPresenter.selectAllProduct(newAllSelect);
                }
            }
        });
    }

    @Override
    public void onProductNumChange(String result, int position, String newNum) {
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

    }

    @Override
    public void onErroy(String message) {

    }

    @Override
    public void showsloading() {

    }

    @Override
    public void hideloading() {

    }

    @Override
    public void showErroy(String message) {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void onDataChanged(List<ShopcarBean> shopcarBeanList) {

    }

    @Override
    public void onOneDataChanged(int position, ShopcarBean shopcarBean) {
        shopCarAdapter.notifyItemChanged(position);
    }

    @Override
    public void onMoneyChanged(String moneyValue) {

    }

    @Override
    public void onAllSelected(boolean isAllSelect) {
        checkBox_all.setChecked(isAllSelect);
    }
}