package com.shopmall.bawei.shopcar;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.shopmall.framework.manager.ShopUserManager;
import com.shopmall.framework.base.BaseMVPActivity;
import com.shopmall.net.bean.LoginBean;

@Route(path = "/shopcar/ShopCarActivity")
public class ShopCarActivity extends BaseMVPActivity implements ShopUserManager.IUserLoginChangeListener {
    private TextView shopText;

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        shopText = (TextView) findViewById(R.id.shopText);
        ShopUserManager.getInstance().registerUserLoginChangeListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_car;
    }

    @Override
    public void OnUserLogin(LoginBean loginBean) {
        shopText.setText(loginBean.getResult().getName());
    }

    @Override
    public void OnUserLogout() {

    }
}