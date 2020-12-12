package com.shopmall.bawei.shopcar;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.base.BaseActivity;
import com.example.framework.manager.CacheManager;
import com.example.framework.view.ToolBar;
import com.example.net.bean.RemoveManyProductBean;
import com.example.net.bean.SelectAllBean;
import com.example.net.bean.ShopCarBean;
import com.example.net.bean.UpdateProductNumBean;
import com.example.net.bean.UpdateProductSelectedBean;
import com.shoppmall.common.adapter.error.ErrorBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Route(path = "/shopCar/ShopCarActivity")
public class ShopCarActivity extends BaseActivity implements CacheManager.IShopcarDataChangeListener, ShopCarContract.ShopCarView,IOnShopCarItemChildClickListener {
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
    private List<ShopCarBean.ResultBean> list=new ArrayList<>();
    private ShopCarAdapter adapter;

    @Override
    protected void initListener() {
        super.initListener();

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
        presenter=new ShopCarPresenterImpl();
        presenter.attchView(this);
    }

    @Override
    protected void initData() {
       intent = getIntent();
        List<ShopCarBean.ResultBean> shopCarList = CacheManager.getInstance().getShopCarList();
        if(shopCarList.size()>0){
            llShopcar.setVisibility(View.VISIBLE);
            llEmpty.setVisibility(View.GONE);
            list.clear();
            list.addAll(shopCarList);
            llCheckAll.setVisibility(View.VISIBLE);
        }else {
            list.clear();
            llShopcar.setVisibility(View.GONE);
            llEmpty.setVisibility(View.VISIBLE);
        }
        adapter.updataData(list);
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
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter=new ShopCarAdapter(this,this);

        recyclerview.setAdapter(adapter);
    }

    @Override
    public void onDataChanged(List<ShopCarBean.ResultBean> shopCarBeanList) {


    }

    @Override
    public void onOneDataChanged(int position, ShopCarBean.ResultBean shopCarBean) {

    }


    @Override
    public void onMoneyChanged(String moneyValue) {

    }

    @Override
    public void onAllSelected(boolean isAllSelect) {

    }

    @Override
    public void onRemoveManyOk(RemoveManyProductBean bean) {

    }

    @Override
    public void onRemoveManyError(ErrorBean bean) {

    }

    @Override
    public void onSelectAllOk(SelectAllBean bean) {

    }

    @Override
    public void onSelectAllError(ErrorBean bean) {

    }

    @Override
    public void onProductNumChangeOk(UpdateProductNumBean bean) {

    }

    @Override
    public void onProductNumChangeError(ErrorBean bean) {

    }

    @Override
    public void onProductSelectChangeOk(UpdateProductSelectedBean bean) {

    }

    @Override
    public void onProductSelectChangeError(ErrorBean bean) {

    }

    @Override
    public void showloading() {

    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void onProductNumChange(String id, int num, String name, String url, String price) {

    }

    @Override
    public void onProductSelectChange(int position, boolean isSelect) {

    }

}