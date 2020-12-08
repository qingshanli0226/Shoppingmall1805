package com.shopmall.bawei.shopmall1805.ui.fragment_main;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.shopmall.bawei.framework.base.BaseFragment;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bean.ShopcarBean;
import com.shopmall.manager.ShopCarmanager;

import java.util.List;

public class ShopCarFragment extends BaseFragment implements ShopCarmanager.IShopcarDataChangeListener {
    private TextView shopFragment;

    @Override
    protected void createViewid(View inflate) {

         ShopCarmanager.getShopCarmanager().registiShopcarDataChangeListener(this);

         shopFragment = inflate.findViewById(R.id.shop_fragment);

    }

    @Override
    protected void createEnvent() {

    }

    @Override
    protected void createData() {

    }

    @Override
    protected int fragmentid() {
        return R.layout.shopcar_fragment;
    }

    @Override
    protected void createPresenter() {

    }


    @Override
    public void shopcarData(List<ShopcarBean.ResultBean> shopcarBeans) {
        Log.e("shopcarFragment",""+shopcarBeans);
    }

    @Override
    public void undateshopcar(int positon, ShopcarBean.ResultBean shopcar) {

    }

    @Override
    public void getMoney(String money) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ShopCarmanager.getShopCarmanager().uniShopcarDataChangeListener(this);
    }
}
