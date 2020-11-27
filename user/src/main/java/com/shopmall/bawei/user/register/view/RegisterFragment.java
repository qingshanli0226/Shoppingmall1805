package com.shopmall.bawei.user.register.view;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.shopmall.bawei.common.ErrorBean;
import com.shopmall.bawei.framework.BaseFragment;
import com.shopmall.bawei.user.LoginRegisterActivity;
import com.shopmall.bawei.user.LoginRegisterActivity.*;
import com.shopmall.bawei.user.R;
import com.shopmall.bawei.user.SwitchFragmentListener;
import com.shopmall.bawei.user.register.contract.RegisterContract;
import com.shopmall.bawei.user.register.contract.RegisterImpl;

public class RegisterFragment<P extends RegisterImpl,V extends RegisterContract.IRegisterView> extends BaseFragment<P,V> implements RegisterContract.IRegisterView, View.OnClickListener {
    public interface RegisterClickListener{

        void onItemClick(View v);
    }
    private SwitchFragmentListener listener;



    private ImageButton ibRegBack;

    private EditText etRegPhone;
    private EditText etRegPwd;
    private ImageButton ibRegVisible;
    private Button btnReg;
    private TextView tvRegRegister;

    private String name;
    private String pwd;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof SwitchFragmentListener) {
            listener = (SwitchFragmentListener) context;
        }
    }

    @Override
    protected void initView() {
        ibRegBack = (ImageButton) findViewById(R.id.ib_reg_back);
        etRegPhone = (EditText) findViewById(R.id.et_reg_phone);
        etRegPwd = (EditText) findViewById(R.id.et_reg_pwd);
        ibRegVisible = (ImageButton) findViewById(R.id.ib_reg_visible);
        btnReg = (Button) findViewById(R.id.btn_reg);
        tvRegRegister = (TextView) findViewById(R.id.tv_reg_register);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.ib_reg_back) {

        } else if (id == R.id.ib_reg_visible) {

        } else if (id == R.id.btn_reg) {
            register();
        }
    }

    private void register() {
        name = etRegPhone.getText().toString();
        pwd = etRegPwd.getText().toString();
        httpPresenter.register(name,pwd);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_register;
    }

    @Override
    protected void initListener() {
        tvRegRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.switchFragment(LoginRegisterActivity.TO_LOGIN);
            }
        });
        ibRegBack.setOnClickListener(this);
        ibRegVisible.setOnClickListener(this);
        btnReg.setOnClickListener(this);
    }

    @Override
    protected void initPresenter() {
        httpPresenter = (P) new RegisterImpl();
    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {
        hideLoadingPage(isSuccess,errorBean);
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
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }

    @Override
    public void onRegister(String registerBean) {
        Toast.makeText(getContext(), ""+registerBean, Toast.LENGTH_SHORT).show();
        listener.switchFragment(LoginRegisterActivity.TO_LOGIN);
        /**
         *
         *
         *
         *
         *
         *
         *
         */
    }


}
