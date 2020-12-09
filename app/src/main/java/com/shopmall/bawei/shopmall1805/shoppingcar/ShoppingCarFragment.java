package com.shopmall.bawei.shopmall1805.shoppingcar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.framework.base.BaseFragment;
import com.example.framework.user.CacheManager;
import com.example.net.bean.ShopCarBean;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;


public class ShoppingCarFragment extends BaseFragment implements CacheManager.IShopcarDataChangeListener {
    private RecyclerView recyclerview;
    private LinearLayout llCheckAll;
    private CheckBox checkboxAll;
    private TextView tvShopcartTotal;
    private Button btnCheckOut;
    private LinearLayout llDelete;
    private CheckBox cbAll;
    private Button btnDelete;
    private Button btnCollection;
    private boolean isEdit=true;

    @Override
    protected void initDate() {
        List<ShopCarBean.ResultBean> shopCarList = CacheManager.getInstance().getShopCarList();
        if(shopCarList.size()>0){
            showSuccess();
            llCheckAll.setVisibility(View.VISIBLE);
        }else {
            showEmptyCarPage();
        }
    }

    @Override
    protected void initLisenter() {
        CacheManager.getInstance().setShopcarDataChangeListener(this);

    }

    @Override
    protected void initView() {
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        llCheckAll = (LinearLayout) findViewById(R.id.ll_check_all);
        checkboxAll = (CheckBox) findViewById(R.id.checkbox_all);
        tvShopcartTotal = (TextView)findViewById(R.id.tv_shopcart_total);
        btnCheckOut = (Button) findViewById(R.id.btn_check_out);
        llDelete = (LinearLayout) findViewById(R.id.ll_delete);
        cbAll = (CheckBox) findViewById(R.id.cb_all);
        btnDelete = (Button) findViewById(R.id.btn_delete);
        btnCollection = (Button) findViewById(R.id.btn_collection);


    }

    @Override
    public void onRightClick() {
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
    protected int getLayoutID() {
        return R.layout.fragment_shopping_car;
    }

    @Override
    public void onDataChanged(List<ShopCarBean.ResultBean> shopCarBeanList) {
        Log.i("Yoyo", "onDataChanged: "+shopCarBeanList.size());
        if(shopCarBeanList.size()>0){
            showSuccess();
            llCheckAll.setVisibility(View.VISIBLE);
        }else {
            showEmptyCarPage();
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