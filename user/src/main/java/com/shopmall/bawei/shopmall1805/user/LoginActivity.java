package com.shopmall.bawei.shopmall1805.user;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.IdRes;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.shopmall.bawei.shopmall1805.common.ShopmallConstant;
import com.shopmall.bawei.shopmall1805.framework.ShopUserManager;
import com.shopmall.bawei.shopmall1805.net.entity.LoginBean;
import com.shopmall.bawei.shopmall1805.framework.BaseMVPActivity;
import com.shopmall.bawei.shopmall1805.user.contract.LoginContract;
import com.shopmall.bawei.shopmall1805.user.presenter.LoginPresenterImpl;

import java.util.HashMap;

import om.shopmall.bawei.shopmall1805.user.R;



@Route(path = ShopmallConstant.LOGIN_ACTIVITY_PATH)
public class LoginActivity extends BaseMVPActivity<LoginPresenterImpl, LoginContract.LoginView> implements LoginContract.LoginView {
    private EditText etLoginPhone;
    private EditText etLoginPwd;
    private Button btnLogin;
    private HashMap<String,String> map=new HashMap<>();
    @Override
    protected void initData() {
        toolbar.setToolBarTitle("登陆");
    }
    @Override
    protected void initView() {
        etLoginPhone = findViewById(R.id.et_login_phone);
        etLoginPwd = findViewById(R.id.et_login_pwd);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = etLoginPhone.getText().toString();
                String pwd = etLoginPwd.getText().toString();
                map.put("name",user);
                map.put("password",pwd);
                httpPresenter.getLoginData(map);

            }
        });
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }
    @Override
    protected void initPresenter() {
        httpPresenter = new  LoginPresenterImpl();
        httpPresenter.attachView(LoginActivity.this);
    }
    @Override
    public void showLoaing() {

    }
    @Override
    public void hideLoading() {

    }
    @Override
    public void showEmpty() {

    }
    @Override
    public void onLoginDate(LoginBean loginBean) {
        if(loginBean.getCode().equals("200")){
            ShopUserManager.getInstance().saveLoginBean(loginBean.getResult());
            finish();
            Toast.makeText(this, loginBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, loginBean.getMessage(), Toast.LENGTH_SHORT).show();
    }
}