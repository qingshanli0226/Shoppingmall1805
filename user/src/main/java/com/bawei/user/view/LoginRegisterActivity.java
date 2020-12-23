package com.bawei.user.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bawei.common.view.ErrorBean;
import com.bawei.common.view.NetConfig;
import com.bawei.framework.BaseActivity;
import com.bawei.framework.IView;
import com.bawei.framework.ShopUserManager;
import com.bawei.net.mode.LoginBean;
import com.bawei.net.mode.RegisterBean;
import com.bawei.user.R;
import com.bawei.user.contact.UserContract;
import com.bawei.user.contact.UserContractImpl;

@Route(path = "/user/LoginRegisterActivity")
public class LoginRegisterActivity extends BaseActivity<UserContractImpl, IView> implements View.OnClickListener, UserContract.IUserView {

    private ImageButton ibLoginBack;
    private EditText etLoginPhone;
    private EditText etLoginPwd;
    private ImageButton ibLoginVisible;
    private Button btnLogin;
    private TextView tvLoginRegister;
    private ImageButton ibWeibo;
    private ImageButton ibQq;
    private ImageButton ibWechat;

    private int count;
    private boolean isLogin = true;

    @Override
    protected int layoutId() {
        return R.layout.activity_login_register;
    }

    @Override
    protected void initView() {
        ibLoginBack = (ImageButton) findViewById(R.id.ib_login_back);
        etLoginPhone = (EditText) findViewById(R.id.et_login_phone);
        etLoginPwd = (EditText) findViewById(R.id.et_login_pwd);
        ibLoginVisible = (ImageButton) findViewById(R.id.ib_login_visible);
        btnLogin = (Button) findViewById(R.id.btn_login);
        tvLoginRegister = (TextView) findViewById(R.id.tv_login_register);
        ibWeibo = (ImageButton) findViewById(R.id.ib_weibo);
        ibQq = (ImageButton) findViewById(R.id.ib_qq);
        ibWechat = (ImageButton) findViewById(R.id.ib_wechat);

        ibLoginBack.setOnClickListener(this);
        ibLoginVisible.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        ibWeibo.setOnClickListener(this);
        ibQq.setOnClickListener(this);
        ibWechat.setOnClickListener(this);
        tvLoginRegister.setOnClickListener(this);
    }

    @Override
    protected void initListener() {


    }

    @Override
    protected void initPresenter() {
        httpPresenter = new UserContractImpl();
    }

    @Override
    public void onClick(View v) {
        if (v == ibLoginBack) {
            finish();
        } else if (v == ibLoginVisible) {
            count++;
            if (count % 2 == 0) {
                ibLoginVisible.setBackgroundResource(R.drawable.new_password_drawable_invisible);
                etLoginPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            } else {
                ibLoginVisible.setBackgroundResource(R.drawable.new_password_drawable_visible);
                etLoginPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            }
        } else if (v == btnLogin) {
            if (btnLogin.getText().toString().trim().equals("注册")) {
                String name = etLoginPhone.getText().toString().trim();
                String password = etLoginPwd.getText().toString().trim();
                httpPresenter.registerUser(name, password);
            } else if (btnLogin.getText().toString().trim().equals("登录")) {
                String name = etLoginPhone.getText().toString().trim();
                String password = etLoginPwd.getText().toString().trim();
                httpPresenter.loginUser(name, password);
            }
        } else if (v == tvLoginRegister) {
            if (isLogin) {
                btnLogin.setText("注册");
                tvLoginRegister.setText("登录账号");
                isLogin = false;
            } else {
                btnLogin.setText("登录");
                tvLoginRegister.setText("注册账号");
                isLogin = true;
            }
        }
    }

    @Override
    public void login(LoginBean loginBean) {
        if (loginBean.getCode().equals("200")) {
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            SharedPreferences sharedPreferences = getSharedPreferences(NetConfig.spName, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(NetConfig.tokenName, loginBean.getResult().getToken());
            editor.commit();

            ShopUserManager.getInstance().saveLoginBean(loginBean);
            ARouter.getInstance().build("/main/MainActivity").withString("name", loginBean.getResult().getName()).navigation();
            finish();
        } else {
            Toast.makeText(this, "登录失败" + loginBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void register(RegisterBean registerBean) {
        if (registerBean.getCode().equals("200")) {
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "注册失败" + registerBean.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void logout(String message) {

    }

    @Override
    public void showLoaDing() {

    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {

    }

    @Override
    public void showEmpty() {

    }
}
