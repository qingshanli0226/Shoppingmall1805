package com.bw.user;

import android.view.View;
import android.widget.EditText;


import com.bw.framework.BaseFragment;
import com.bw.user.contract.RegisterContract;
import com.bw.user.presenter.RegisterPresenterImpl;
import com.shopmall.bawei.user.R;



public class RegisterFragment extends BaseFragment<RegisterPresenterImpl, RegisterContract.IRegisterView>
        implements RegisterContract.IRegisterView, View.OnClickListener {
    private EditText nameEditText;
    private EditText passwordEditText;



    @Override
    protected void initPresenter() {
        httpPresenter = new RegisterPresenterImpl();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        nameEditText = view.findViewById(R.id.name);
        passwordEditText = view.findViewById(R.id.password);

        view.findViewById(R.id.registerButton).setOnClickListener(this);
        view.findViewById(R.id.loginTv).setOnClickListener(this);
    }



    @Override
    public void onRegister(String result) {
        switchLoginFragment();
    }



    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.registerButton) {
            register();
        } else if (v.getId() == R.id.loginTv) {
            switchLoginFragment();
        }

    }

    private void switchLoginFragment() {
        LoginRegisterActivity loginRegisterActivity = (LoginRegisterActivity)getActivity();
        loginRegisterActivity.switchFragment(0);
    }

    private void register() {
        httpPresenter.register(nameEditText.getText().toString().trim(), passwordEditText.getText().toString().trim());
    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void showsLoaing() {

    }

    @Override
    public void hidesLoading(boolean isSuccess) {

    }


    @Override
    public void showEmpty() {

    }
}
