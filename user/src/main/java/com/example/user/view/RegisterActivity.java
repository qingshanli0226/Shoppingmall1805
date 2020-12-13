package com.example.user.view;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.framwork.BaseMVPActivity;
import com.example.net.bean.RegisterBean;
import com.example.user.R;
import com.example.user.contract.RegisterContract;
import com.example.user.presenter.RegisterPresenterImpl;

public class RegisterActivity extends BaseMVPActivity<RegisterPresenterImpl, RegisterContract.IRegisterView> implements RegisterContract.IRegisterView{
    private EditText etLoginPhone;
    private EditText etLoginPwd;
    private Button btnLogin;
    private RegisterPresenterImpl registerPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.register_activity;
    }

    @Override
    protected void iniView() {
        etLoginPhone = findViewById(R.id.et_login_phone);
        etLoginPwd = findViewById(R.id.et_login_pwd);
        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

    }

    private void register() {
        registerPresenter.getRegister(etLoginPhone.getText().toString(),etLoginPwd.getText().toString());
    }

    @Override
    protected void initPresenter() {
        registerPresenter=new RegisterPresenterImpl();
        registerPresenter.attatch(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onRegister(RegisterBean registerBean) {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(intent);

    }

    @Override
    public void onError(String code, String message) {
        Toast.makeText(this, "失败"+code+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        registerPresenter.detachview();
    }
}
