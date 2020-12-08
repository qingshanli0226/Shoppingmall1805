package com.shopmall.bawei.shopcar;

import android.util.Log;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.shopmall.bawei.framework.base.BaseActivity;
import com.shopmall.bean.Loginbean;
import com.shopmall.manager.ShopUserManager;

@Route(path = "/shopcar/ShopCarMainActivity")
public class ShopCarMainActivity extends BaseActivity implements ShopUserManager.IUserListener {

    private TextView shopcarText;

    @Override
    protected void oncreatePresenter() {

    }

    @Override
    protected void initEnvent() {

    }

    @Override
    protected void initview() {

        shopcarText = findViewById(R.id.shopcar_text);
        ShopUserManager.getInstance().RegistUserLogin(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int layoutid() {
        return R.layout.shopcar_activity_main;
    }

    /**
     * 用来通知用户登陆的方法
     * @param loginbean
     */
    @Override
    public void OnUserLogin(final Loginbean loginbean) {
        Log.e("shopcar",""+loginbean.getResult().getName());

                shopcarText.setText(loginbean.getResult().getName());


    }

    @Override
    public void OnUserLoginout() {

    }
}
