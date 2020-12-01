package com.shopmall.bawei.user.login.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.common.ErrorBean;
import com.shopmall.bawei.common.UrlHelper;
import com.shopmall.bawei.framework.BaseFragment;
import com.shopmall.bawei.framework.UserManager;
import com.shopmall.bawei.net.mode.LoginBean;
import com.shopmall.bawei.user.LoginRegisterActivity;
import com.shopmall.bawei.user.LoginRegisterActivity.*;
import com.shopmall.bawei.user.R;


import com.shopmall.bawei.user.SwitchFragmentListener;
import com.shopmall.bawei.user.login.contract.LoginContract;
import com.shopmall.bawei.user.login.contract.LoginImpl;

import org.greenrobot.eventbus.EventBus;

public class LoginFragment<P extends LoginImpl,V extends LoginContract.ILoginView> extends BaseFragment<P,V> implements LoginContract.ILoginView, View.OnClickListener {


    public interface LoginClickListener{
        void onItemClick(View v);
    }
    private SwitchFragmentListener listener;

    private ImageButton ibLoginBack;
    private EditText etLoginPhone;
    private EditText etLoginPwd;
    private ImageButton ibLoginVisible;
    private Button btnLogin;
    private TextView tvLoginRegister;
    private TextView tvLoginForgetPwd;
    private ImageButton ibWeibo;
    private ImageButton ibQq;
    private ImageButton ibWechat;





    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof SwitchFragmentListener) {
            listener = (SwitchFragmentListener) context;
        }
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
        ibWeibo = (ImageButton) findViewById(R.id.ib_weibo);
        ibQq = (ImageButton) findViewById(R.id.ib_qq);
        ibWechat = (ImageButton) findViewById(R.id.ib_wechat);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.ib_login_back) {

        } else if (id == R.id.ib_login_visible) {

        } else if (id == R.id.btn_login) {
            login();
        } else if (id == R.id.tv_login_forget_pwd) {

        } else if (id == R.id.ib_weibo) {

        } else if (id == R.id.ib_qq) {

        } else if (id == R.id.ib_wechat) {

        }
    }

    private void login() {
        String name = etLoginPhone.getText().toString();
        String pwd = etLoginPwd.getText().toString();
        httpPresenter.login(name,pwd);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_login;
    }
    @Override
    protected void initListener() {
        tvLoginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.switchFragment(LoginRegisterActivity.TO_REG);
            }
        });
        ibLoginBack.setOnClickListener(this);
        etLoginPhone.setOnClickListener(this);
        etLoginPwd.setOnClickListener(this);
        ibLoginVisible.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        tvLoginForgetPwd.setOnClickListener(this);
        ibWeibo.setOnClickListener(this);
        ibQq.setOnClickListener(this);
        ibWechat.setOnClickListener(this);
    }

    @Override
    protected void initPresenter() {
        httpPresenter = (P) new LoginImpl();
    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void showLoaDing() {
        showLoading();
    }

    @Override
    public void showEmpty() {
        showEmptyPa();
    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {
        hideLoadingPage(isSuccess,errorBean);
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }

    @Override
    public void onLogin(LoginBean loginBean) {
        UserManager.getInstance().saveLoginBean(loginBean);
//        EventBus.getDefault().post(loginBean);

        ARouter.getInstance().build("/app/MainActivity").navigation();
        /**
         * T
         * O
         * K
         * E
         * N
         *存
         *储
         *到
         * S
         * P
         *
         *
         *
         */
    }



}
