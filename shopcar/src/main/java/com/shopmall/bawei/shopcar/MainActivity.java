package com.shopmall.bawei.shopcar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    private TextView bianji,total;
    private ShopCarAdapter shopCarAdapter;
    private boolean i;
    private LinearLayout linearLayout,linearLayout1;
    private CheckBox checkBox_all,checkBox_etAll;
    private boolean newAllSelect;
    private  List<ShopcarBean> shopcarBeanList;
    private Button button_delete,btn_checkout;
    private boolean isEditMode = false;

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
        total = findViewById(R.id.tv_shopcart_total);
        checkBox_etAll = findViewById(R.id.cb_all);
        button_delete = findViewById(R.id.btn_delete);
        btn_checkout = findViewById(R.id.btn_check_out);
    }

    @Override
    protected void initData() {
        ARouter.getInstance().inject(this);

        httpPresenter = new ShopcarPresenterImpl();

        ARouter.getInstance().inject(this);

        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build("/me/order")
                        .navigation();
            }
        });

        //删除
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<ShopcarBean> deleteShopcarBeanList = CacheManager.getInstance().getDeleteShopcarBeanList();
                if (deleteShopcarBeanList.size()>0){
                    httpPresenter.deleteProducts(deleteShopcarBeanList);
                }else {
                    Toast.makeText(MainActivity.this, "当前没有要删除的数据", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEditMode){
                    isEditMode = false;
                    shopCarAdapter.setEditMode(isEditMode);
                    linearLayout1.setVisibility(View.VISIBLE);
                    linearLayout.setVisibility(View.GONE);

                }else {
                    //编辑模式下
                    isEditMode = true;
                    linearLayout1.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.VISIBLE);
                    shopCarAdapter.setEditMode(isEditMode);
                    if (CacheManager.getInstance().isAllSelectInEditMode()) {
                        checkBox_etAll.setChecked(true);
                    }
                    Log.i("TAG", "onClick: "+CacheManager.getInstance().getDeleteShopcarBeanList().size());
                }
            }
        });




        shopcarBeanList = CacheManager.getInstance().getShopcarBeanList();
        shopCarAdapter = new ShopCarAdapter(R.layout.item_shopcar,shopcarBeanList);
        recyclerView.setAdapter(shopCarAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        shopCarAdapter.setShopcarPresenter(httpPresenter);
        total.setText(CacheManager.getInstance().getMoneyValue());



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

        checkBox_etAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox_etAll.isChecked()){
                    CacheManager.getInstance().selectAllProductInEditMode(true);
                }else {
                    CacheManager.getInstance().selectAllProductInEditMode(false);
                }
            }
        });
    }




    @Override
    public void onProductNumChange(String result, int position, String newNum) {
        Toast.makeText(this, "修改产品数量", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
        CacheManager.getInstance().processDeleteProducts();
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
        this.shopcarBeanList = shopcarBeanList;
        shopCarAdapter.notifyDataSetChanged();
    }

    @Override
    public void onOneDataChanged(int position, ShopcarBean shopcarBean) {
        shopCarAdapter.notifyItemChanged(position);
    }

    @Override
    public void onMoneyChanged(String moneyValue) {
        total.setText(moneyValue);
    }

    @Override
    public void onAllSelected(boolean isAllSelect) {
        if (isEditMode){
            checkBox_etAll.setChecked(isAllSelect);
        }else {
            checkBox_all.setChecked(isAllSelect);
        }
    }


}