package com.bawei.user.view;

import android.os.Bundle;

import com.bawei.framework.BaseActivity;
import com.bawei.user.R;

public class LoginRegisterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
    }

    @Override
    protected int layoutId() {
        return 0;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initPresenter() {

    }
}
