package com.bawei.user.view;

import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bawei.common.view.ErrorBean;
import com.bawei.framework.BaseActivity;
import com.bawei.framework.IView;
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
    private TextView tvLoginForgetPwd;
    private ImageButton ib_weibo;
    private ImageButton ib_qq;
    private ImageButton ib_wechat;
    private RegisterBean registerBean;

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
        tvLoginForgetPwd = (TextView) findViewById(R.id.tv_login_forget_pwd);
        ib_weibo = (ImageButton) findViewById(R.id.ib_weibo);
        ib_qq = (ImageButton) findViewById(R.id.ib_qq);
        ib_wechat = (ImageButton) findViewById(R.id.ib_wechat);

        ibLoginBack.setOnClickListener(this);
        ibLoginVisible.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        ib_weibo.setOnClickListener(this);
        ib_qq.setOnClickListener(this);
        ib_wechat.setOnClickListener(this);
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
        ARouter.getInstance().build("/main/MainActivity").navigation();
        finish();
    }

    @Override
    public void register(RegisterBean registerBean) {

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
