package com.shopmall.bawei.user.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.common2.LoginBean;
import com.example.common2.RegisterBean;
import com.shopmall.bawei.user.R;
import com.shopmall.bawei.user.contract.UserContract;
import com.shopmall.bawei.user.presenter.UserPresneter;

import mvp.view.BaseMVPActivity;

public class UserMainActivity extends BaseMVPActivity<UserPresneter, UserContract.IUser> implements UserContract.IUser {


    private ImageButton ibLoginBack;
    private EditText etLoginPhone;
    private EditText etLoginPwd;
    private Button btnLogin;
 


    @Override
    protected void initView() {
        ibLoginBack = (ImageButton) findViewById(R.id.ib_login_back);
        etLoginPhone = (EditText) findViewById(R.id.et_login_phone);
        etLoginPwd = (EditText) findViewById(R.id.et_login_pwd);
        btnLogin = (Button) findViewById(R.id.btn_login);

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
        // TODO: 2020/12/8 今天就写到这啦,下班
    }

    @Override
    protected void initData() {
        
    }


    @Override
    public void onLogin(LoginBean loginBean) {

    }

    @Override
    public void onRegister(RegisterBean registerBean) {

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