package com.shopmall.bawei.shopmall1805.fragment;

import android.os.Build;
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

import com.shopmall.bawei.shopcar.ShopCarAdapter;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.framework.base.BaseMVPFragment;
import com.shopmall.framework.manager.CacheManager;
import com.shopmall.framework.service.ShopCarNet;
import com.shopmall.net.bean.ShopcarBean;

import java.util.List;

public class CartFragment extends BaseMVPFragment implements CacheManager.IShopCarDataChangeListener {
    private TextView shopCarEdit;
    private RecyclerView shopCarRecycle;
    private CheckBox shopCarAllSelect;
    private TextView shopCarMoney;
    private Button shopCarCompute;
    private CheckBox checkBox;

    private ShopCarAdapter shopCarAdapter;

    @Override
    protected void createViewid(View inflate) {
        CacheManager.getInstance().setShopCarDataChangeListener(this);

        shopCarEdit = (TextView) inflate.findViewById(R.id.shopCar_edit);
        shopCarRecycle = (RecyclerView) inflate.findViewById(R.id.shopCar_recycle);
        shopCarAllSelect = (CheckBox) inflate.findViewById(R.id.shopCar_allSelect);
        shopCarMoney = (TextView) inflate.findViewById(R.id.shopCar_money);
        shopCarCompute = (Button) inflate.findViewById(R.id.shopCar_compute);

    }

    @Override
    protected void createEnvent() {
        final PopupWindow popupWindow = new PopupWindow();
        View inflate = LayoutInflater.from(getContext()).inflate(com.shopmall.bawei.shopcar.R.layout.popu_shopcar, null);
        popupWindow.setContentView(inflate);
        popupWindow.setWidth(RecyclerView.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(100);
        checkBox = inflate.findViewById(com.shopmall.bawei.shopcar.R.id.popu_shopCar_allSelect);
        Button buttonDelete = inflate.findViewById(com.shopmall.bawei.shopcar.R.id.popu_shopCar_delect);

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


    }

    @Override
    protected void createData() {
        shopCarAdapter = new ShopCarAdapter();
        shopCarRecycle.setAdapter(shopCarAdapter);
        shopCarRecycle.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected int fragmentid() {
        return R.layout.fragment_cart;
    }

    @Override
    protected void createPresenter() {

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
    public void onDestroy() {
        super.onDestroy();
        CacheManager.getInstance().unSetShopCarDataChangerListener(this);
    }
}