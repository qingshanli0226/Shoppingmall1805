package com.shopmall.bawei.shopmall1805.shopcar;


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.shopmall.bawei.shopcar.R;
import com.shopmall.bawei.shopmall1805.common.ShopmallConstant;
import com.shopmall.bawei.shopmall1805.framework.BaseMVPActivity;
import com.shopmall.bawei.shopmall1805.framework.view.Toolbar;
import com.shopmall.bawei.shopmall1805.net.entity.ShopcarBean;
import com.shopmall.bawei.shopmall1805.shopcar.adapter.ShopCarAdapter;
import com.shopmall.bawei.shopmall1805.shopcar.contract.ShopcarContract;
import com.shopmall.bawei.shopmall1805.shopcar.presenter.ShopcarPresenterImpl;

import java.util.ArrayList;
import java.util.List;


@Route(path = ShopmallConstant.SHOP_CAR_ACTIVITY_PATH)
public class ShopCarActivity extends BaseMVPActivity<ShopcarPresenterImpl, ShopcarContract.IShopcarView> implements ShopcarContract.IShopcarView {
    private Toolbar toolbar;
    private RecyclerView shopcarRv;
    private RelativeLayout shaopcar;
    private RelativeLayout shaopcarCompileRl;
    private RadioButton shopcarCompileCheckAll;
    private Button shopcarCompileCollectBt;
    private Button shopcarCompileDeleteBt;
    private RelativeLayout shaopcarAccomplishRl;
    private RadioButton shopcarAccomplishCheckAll;
    private TextView shopcarAccomplishPrice;
    private boolean bok= true;
    private List<ShopcarBean> list=new ArrayList<>();
    private ShopCarAdapter shopCarAdapter;

    @Override
    protected void initPresenter() {
        httpPresenter = new ShopcarPresenterImpl();
        httpPresenter.getOrderInfo();
    }
    @Override
    protected void initData() {
        toolbar.setToolBarTitle("购物车");
        toolbar.setToolbarRightTv("编辑");
        toolbar.setToolBarClickListner(new Toolbar.IToolBarClickListner() {
            @Override
            public void onLeftClick() {
                finish();
            }
            @Override
            public void onRightClick() {
                if(bok == true){
                    bok = false;
                    toolbar.setToolbarRightTv("完成");
                    shaopcarAccomplishRl.setVisibility(View.GONE);
                    shaopcarCompileRl.setVisibility(View.VISIBLE);
                }else {
                    bok = true;
                    toolbar.setToolbarRightTv("编辑");
                    shaopcarAccomplishRl.setVisibility(View.VISIBLE);
                    shaopcarCompileRl.setVisibility(View.GONE);
                }
            }
        });
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

        shopcarRv.setLayoutManager(new LinearLayoutManager(this));
        shopCarAdapter = new ShopCarAdapter(R.layout.item_shopcar,list);
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
    public void onOrderInfo(List<ShopcarBean> beanList) {
        Log.i("TAG", "onOrderInfo: "+beanList);
        if(beanList!=null){
            list.addAll(beanList);
            shopCarAdapter.notifyDataSetChanged();
        }
    }
}