package com.shopmall.bawei.user.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.framework.BaseFragment;
import com.shopmall.bawei.user.LoginRegisterActivity;
import com.shopmall.bawei.user.R;
import com.shopmall.bawei.user.contract.RegisterContract;
import com.shopmall.bawei.user.presenter.RegisPresenter;

public  class RegisterFragment extends BaseFragment<RegisPresenter, RegisterContract.RegisterView>implements RegisterContract.RegisterView {


    private ImageButton ibLoginBack;
    private EditText etRegisterPhone;
    private EditText etRegisterPwd;
    private ImageButton ibLoginVisible;
    private Button btnRegister;
    private TextView tvRegisterLogin;

    protected void initView(View view) {
        ibLoginBack = (ImageButton) view.findViewById(R.id.ib_login_back);
        etRegisterPhone = (EditText) view.findViewById(R.id.et_register_phone);
        etRegisterPwd = (EditText) view.findViewById(R.id.et_register_pwd);
        ibLoginVisible = (ImageButton) view.findViewById(R.id.ib_login_visible);
        btnRegister = (Button) view.findViewById(R.id.btn_register);
        tvRegisterLogin = (TextView) view.findViewById(R.id.tv_register_login);
    }

    @Override
    protected void initPresenter() {
        httpPresenter = new RegisPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register;
    }

    @Override
    protected void initData() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etRegisterPhone.getText().toString();
                String password = etRegisterPwd.getText().toString();
                httpPresenter.getMessage(name,password);
            }
        });


        tvRegisterLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginRegisterActivity.vrLoginRegister.setCurrentItem(0);
            }
        });
    }

    @Override
    public void getViewData1(String message) {
        Log.i("wftregister", "getViewData1: "+message);
        if (message.equals("注册成功")){
            LoginRegisterActivity.vrLoginRegister.setCurrentItem(0);
        }else {
            Toast.makeText(getContext(), "注册失败，请重新注册", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onErroy(String message) {

    }

    @Override
    public void showsloading() {

    }

    @Override
    public void hideloading() {

    }

    @Override
    public void showErroy(String message) {

    }

    @Override
    public void showEmpty() {

    }
}