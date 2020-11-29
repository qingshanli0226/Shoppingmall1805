package com.shopmall.bawei.user;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.shopmall.bawei.common.ErrorBean;
import com.shopmall.bawei.framework.BaseFragment;
import com.shopmall.bawei.user.contract.RegisterContract;
import com.shopmall.bawei.user.presenter.RegisterPresenterImpl;

public class RegisterFragment extends BaseFragment<RegisterPresenterImpl, RegisterContract.IRegisterView> implements RegisterContract.IRegisterView,LoginRegisterActivity.INameInterface,View.OnClickListener{
    private EditText nameEditText;
    private EditText passwordEditText;
    @Override
    protected int layoutId() {
        return R.layout.fragment_register;
    }

    @Override
    protected void initListener() {

    }
    @Override
    protected void initView() {
        nameEditText = findViewById(R.id.name);
        passwordEditText = findViewById(R.id.password);

        findViewById(R.id.registerButton).setOnClickListener(this);
        findViewById(R.id.loginTv).setOnClickListener(this);
    }

    @Override
    protected void initPresenter() {
        httpPresenter = new RegisterPresenterImpl();
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }


    @Override
    public void onRegister(String result) {
        Toast.makeText(getContext(), "注册成功", Toast.LENGTH_SHORT).show();
        switchLoginFragment();
    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {

    }



    private void switchLoginFragment() {
        LoginRegisterActivity loginRegisterActivity = (LoginRegisterActivity)getActivity();
        loginRegisterActivity.switchFragment(0, nameEditText.getText().toString().trim());//
    }

    private void register() {
        httpPresenter.register(nameEditText.getText().toString().trim(), passwordEditText.getText().toString().trim());
    }

    @Override
    public void setName(String name) {
        nameEditText.setText(name);
        passwordEditText.setText("");
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.registerButton) {
            register();
        } else if (view.getId() == R.id.loginTv) {
            switchLoginFragment();
        }
    }
}
