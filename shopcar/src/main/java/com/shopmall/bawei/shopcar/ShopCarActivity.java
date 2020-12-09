package com.shopmall.bawei.shopcar;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.base.BaseActivity;
import com.example.framework.user.CacheManager;
import com.example.framework.view.ToolBar;
import com.example.net.bean.ShopCarBean;

import java.io.Serializable;
import java.util.List;

@Route(path = "/shopCar/ShopCarActivity")
public class ShopCarActivity extends BaseActivity implements CacheManager.IShopcarDataChangeListener {
    private RecyclerView recyclerview;
    private LinearLayout llCheckAll;
    private CheckBox checkboxAll;
    private TextView tvShopcartTotal;
    private Button btnCheckOut;
    private LinearLayout llDelete;
    private CheckBox cbAll;
    private Button btnDelete;
    private Button btnCollection;
    private  Intent intent;
    private boolean flag=false;
    private ToolBar emptyToolbar;
    private LinearLayout llEmpty;
    private boolean isEdit=true;
    private LinearLayout llShopcar;

    @Override
    protected void initListener() {
        super.initListener();

        emptyToolbar.setToolBarClickListner(new ToolBar.IToolBarClickListner() {
            @Override
            public void onLeftClick() {
                String type = intent.getStringExtra("type");
                Serializable extra = intent.getSerializableExtra("good");
                ARouter.getInstance().build("/detailpage/DetailActivity").withSerializable("good",extra).withString("type",type).navigation();
                finish();
            }

            @Override
            public void onRightClick() {

            }
        });
        CacheManager.getInstance().setShopcarDataChangeListener(this);
    }

    @Override
    public void onLeftClick() {
        super.onLeftClick();
        String type = intent.getStringExtra("type");
        Serializable extra = intent.getSerializableExtra("good");
        ARouter.getInstance().build("/detailpage/DetailActivity").withSerializable("good",extra).withString("type",type).navigation();
        finish();
    }

    @Override
    public void onRightClick() {
        super.onRightClick();
        if(isEdit){
            toolBar.setToolBarRightTv("完成");
            llDelete.setVisibility(View.VISIBLE);
            llCheckAll.setVisibility(View.GONE);
        }else {
            toolBar.setToolBarRightTv("编辑");
            llDelete.setVisibility(View.GONE);
            llCheckAll.setVisibility(View.VISIBLE);
        }
        isEdit=!isEdit;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
       intent = getIntent();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_shop_car;
    }

    @Override
    protected void initView(){
        llShopcar = (LinearLayout) findViewById(R.id.ll_shopcar);
        llEmpty = (LinearLayout) findViewById(R.id.ll_empty);
        emptyToolbar = (ToolBar) findViewById(R.id.empty_toolbar);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        llCheckAll = (LinearLayout) findViewById(R.id.ll_check_all);
        checkboxAll = (CheckBox) findViewById(R.id.checkbox_all);
        tvShopcartTotal = (TextView) findViewById(R.id.tv_shopcart_total);
        btnCheckOut = (Button) findViewById(R.id.btn_check_out);
        llDelete = (LinearLayout) findViewById(R.id.ll_delete);
        cbAll = (CheckBox) findViewById(R.id.cb_all);
        btnDelete = (Button) findViewById(R.id.btn_delete);
        btnCollection = (Button) findViewById(R.id.btn_collection);

    }

    @Override
    public void onDataChanged(List<ShopCarBean.ResultBean> shopCarBeanList) {
        if(shopCarBeanList.size()>0){
            llShopcar.setVisibility(View.VISIBLE);
            llEmpty.setVisibility(View.GONE);
        }else {
            llShopcar.setVisibility(View.GONE);
            llEmpty.setVisibility(View.VISIBLE);
        }
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