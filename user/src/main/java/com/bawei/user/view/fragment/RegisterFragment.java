package com.bawei.user.view.fragment;


import android.view.View;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.bawei.common.view.ErrorBean;
import com.bawei.common.view.MyToolBar;
import com.bawei.framework.BaseFragment;
import com.bawei.net.mode.LoginBean;
import com.bawei.net.mode.RegisterBean;
import com.bawei.user.R;
import com.bawei.user.contact.UserContract;
import com.bawei.user.contact.UserContractImpl;
import com.bawei.user.view.LoginRegisterActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends BaseFragment<UserContractImpl, UserContract.IUserView> implements View.OnClickListener, UserContract.IUserView {

    private MyToolBar toolbar;
    private EditText name;
    private EditText password;

    @Override
    protected int layoutId() {
        return R.layout.fragment_register;
    }

    @Override
    protected void initView() {
        toolbar = findViewById(R.id.toolbar);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        findViewById(R.id.loginButton).setOnClickListener(this);
        findViewById(R.id.registerTv).setOnClickListener(this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initPresenter() {
        httpPresenter = new UserContractImpl();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.registerButton) {
            registeruser();
        } else if (view.getId() == R.id.loginTv) {
            switchLoginFragment();
        }
    }

    private void registeruser() {
        httpPresenter.registerUser(name.getText().toString().trim(), password.getText().toString().trim());
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }

    @Override
    public void login(LoginBean loginBean) {

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

    private void switchLoginFragment() {
        LoginRegisterActivity loginRegisterActivity = (LoginRegisterActivity) getActivity();
        loginRegisterActivity.switchFragment(0, name.getText().toString().trim());//
    }
}
