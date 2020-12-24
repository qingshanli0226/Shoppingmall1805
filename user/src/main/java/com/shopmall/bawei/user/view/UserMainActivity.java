package com.shopmall.bawei.user.view;


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.common2.LoginBean;
import com.example.common2.RegisterBean;
import com.shopmall.bawei.user.R;
import com.shopmall.bawei.user.contract.UserContract;
import com.shopmall.bawei.user.presenter.UserPresneter;

import mvp.view.BaseMVPActivity;
import mvp.ShopUserManager;

public class UserMainActivity extends BaseMVPActivity<UserPresneter, UserContract.IUser> implements UserContract.IUser {
    private ImageButton ibLoginBack;
    private EditText etLoginPhone;
    private EditText etLoginPwd;
    private Button btnLogin;
    private Button btnRegister;

    @Override
    protected void initView() {
        ibLoginBack = (ImageButton) findViewById(R.id.ib_login_back);
        etLoginPhone = (EditText) findViewById(R.id.et_login_phone);
        etLoginPwd = (EditText) findViewById(R.id.et_login_pwd);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnRegister = (Button) findViewById(R.id.btn_register);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = etLoginPhone.getText().toString().trim();
                String text1 = etLoginPwd.getText().toString().trim();
                Log.i("TAG", "onClick: " + text + text1);
                ihttpPresenter.getILoginBean(text, text1);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = etLoginPhone.getText().toString().trim();
                String text1 = etLoginPwd.getText().toString().trim();
                ihttpPresenter.getIRegisterBean(text, text1);
            }
        });
        ibLoginBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_main;
    }

    @Override
    protected void initPresenter() {


        ihttpPresenter = new UserPresneter();
        ihttpPresenter.attachView(this);


    }

    @Override
    protected void initData() {

    }

    @Override
    public void onLogin(LoginBean loginBean) {

        if (loginBean.getCode().equals("200")) {
            ShopUserManager.getInstance().saveLoginBean(loginBean);
            finish();
        }

    }

    @Override
    public void onRegister(RegisterBean registerBean) {

        Log.i("TAG", "onLogin: " + registerBean.getMessage());
    }

    @Override
    public void showError(String code, String message) {

    }

    @Override
    public void showLoaing() {

    }

    @Override
    public void hideLoading() {

    }
}