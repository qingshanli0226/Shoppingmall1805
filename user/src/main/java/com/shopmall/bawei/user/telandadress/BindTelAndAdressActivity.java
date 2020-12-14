package com.shopmall.bawei.user.telandadress;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.framework.base.BaseActivity;
import com.shopmall.bawei.user.R;
@Route(path = "/user/BindTelAndAdressActivity")
public class BindTelAndAdressActivity extends BaseActivity {

    @Override
    protected void initPresenter() {

    }

    @Override
    public void onLeftClick() {
        super.onLeftClick();
        finish();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_bind_tel_and_adress;
    }

    @Override
    protected void initView() {

    }
}