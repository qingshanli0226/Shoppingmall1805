package com.shopmall.bawei.shopcar;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.shopmall.common.Constants;
import com.shopmall.framework.base.BaseMVPActivity;
import com.shopmall.framework.manager.CacheManager;
import com.shopmall.framework.service.ShopCarNet;
import com.shopmall.net.bean.ShopcarBean;

import java.util.ArrayList;
import java.util.List;

@Route(path = "/shopcar/ShopCarActivity")
public class ShopCarActivity extends BaseMVPActivity implements CacheManager.IShopCarDataChangeListener {
    private TextView shopCarEdit;
    private RecyclerView shopCarRecycle;
    private CheckBox shopCarAllSelect;
    private TextView shopCarMoney;
    private Button shopCarCompute;
    private CheckBox checkBox;

    private ShopCarAdapter shopCarAdapter;
    private List<ShopcarBean.ResultBean> shopCarBeanList = new ArrayList<>();

    @Override
    protected void initEvent() {
        final PopupWindow popupWindow = new PopupWindow();
        View inflate = LayoutInflater.from(this).inflate(R.layout.popu_shopcar, null);
        popupWindow.setContentView(inflate);
        popupWindow.setWidth(RecyclerView.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(100);
        checkBox = inflate.findViewById(R.id.popu_shopCar_allSelect);
        Button buttonDelete = inflate.findViewById(R.id.popu_shopCar_delect);

        shopCarEdit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                String s = shopCarEdit.getText().toString();
                if (s.equals("编辑")){
                    popupWindow.showAsDropDown(shopCarRecycle,0,0, Gravity.BOTTOM);
                    shopCarAdapter.setIsSelect(true);
                    shopCarEdit.setText("完成");
                }else {
                    shopCarAdapter.setIsSelect(false);
                    popupWindow.dismiss();
                    shopCarEdit.setText("编辑");
                }
            }
        });

        shopCarAllSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShopCarNet.getShopCarNet().selectAllProduct(shopCarAllSelect.isChecked());
            }
        });

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked()){
                    CacheManager.getInstance().selectAllProductInEditMode(true);
                }else {
                    CacheManager.getInstance().selectAllProductInEditMode(false);
                }
                shopCarAdapter.notifyDataSetChanged();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShopCarNet.getShopCarNet().removeManyProduct(Constants.REMOVE_MANYPRODUCT);
                shopCarAdapter.setIsSelect(false);
                shopCarEdit.setText("编辑");
                popupWindow.dismiss();
            }
        });
    }

    @Override
    protected void initData() {
        shopCarBeanList = CacheManager.getInstance().getShopCarBeanList();
        if (shopCarBeanList == null){
            Toast.makeText(this, "请添加商品", Toast.LENGTH_SHORT).show();
            return;
        }
        shopCarMoney.setText(CacheManager.getInstance().getMoneyValue());

        shopCarAdapter = new ShopCarAdapter();
        shopCarRecycle.setAdapter(shopCarAdapter);
        shopCarRecycle.setLayoutManager(new LinearLayoutManager(this));

        shopCarAdapter.updataData(shopCarBeanList);

        shopCarAllSelect.setChecked(CacheManager.getInstance().isAllSelected());
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        CacheManager.getInstance().setShopCarDataChangeListener(this);

        shopCarEdit = (TextView) findViewById(R.id.shopCar_edit);
        shopCarRecycle = (RecyclerView) findViewById(R.id.shopCar_recycle);
        shopCarAllSelect = (CheckBox) findViewById(R.id.shopCar_allSelect);
        shopCarMoney = (TextView) findViewById(R.id.shopCar_money);
        shopCarCompute = (Button) findViewById(R.id.shopCar_compute);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_car;
    }

    @Override
    public void onDataChanged(List<ShopcarBean.ResultBean> shopCarBeanList) {
        shopCarAdapter.updataData(shopCarBeanList);
    }

    @Override
    public void onOneDataChanged(int position, ShopcarBean.ResultBean shopcarBean) {
        shopCarAdapter.notifyItemChanged(position);
    }

    @Override
    public void onMoneyChanged(String moneyValue) {
        shopCarMoney.setText(moneyValue+"");
    }

    @Override
    public void onAllSelected(boolean isAllSelect) {
        shopCarAllSelect.setChecked(isAllSelect);
    }

    @Override
    public void getDeleteAllSelect(boolean isAllSelect) {
        checkBox.setChecked(isAllSelect);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CacheManager.getInstance().unSetShopCarDataChangerListener(this);
    }
}