package com.example.user.view;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framwork.BaseMVPActivity;
import com.example.framwork.ShopUserManager;
import com.example.net.NetBusinessException;
import com.example.net.bean.ErrorBean;
import com.example.net.bean.LoginBean;
import com.example.user.R;
import com.example.user.contract.LoginContract;
import com.example.user.presenter.LoginPresenterImpl;

@Route(path = "/login/loginActivity")
public class LoginActivity extends BaseMVPActivity<LoginPresenterImpl, LoginContract.ILoginView> implements LoginContract.ILoginView{
    private EditText etLoginPhone;
    private EditText etLoginPwd;
    private Button btnLogin;
    private TextView tvLoginRegister;
    private LoginPresenterImpl loginPresenter;


    @Override
    protected void iniHttpView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.login_activity;
    }

    @Override
    protected void initView() {




    }

    private void login() {
        loginPresenter.getLogin(etLoginPhone.getText().toString(),etLoginPwd.getText().toString());
    }

    @Override
    protected void initPresenter() {


        loginPresenter=new LoginPresenterImpl();
        loginPresenter.attatch(this);
    }

    @Override
    protected void initData() {
        etLoginPhone = findViewById(R.id.et_login_phone);
        etLoginPwd = findViewById(R.id.et_login_pwd);
        btnLogin = findViewById(R.id.btn_login);
        tvLoginRegister = findViewById(R.id.tv_login_register);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        tvLoginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onLogin(LoginBean loginBean) {
        Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
        //存储login
        ShopUserManager.getInstance().saveLoginbean(loginBean);
        ARouter.getInstance().build("/Main/MainActivity").navigation();
    }

    @Override
    public void onError(String code, String message) {
        Toast.makeText(this, "失败"+code+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadings() {
        showLoading();
    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean message) {
        hideLoadingPage(isSuccess,message);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.detachview();

    }
}
