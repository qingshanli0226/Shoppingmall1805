package com.shopmall.bawei.user.login.view;

import com.shopmall.bawei.common.ErrorBean;
import com.shopmall.bawei.framework.BaseFragment;
import com.shopmall.bawei.net.mode.LoginBean;
import com.shopmall.bawei.user.login.contract.LoginContract;
import com.shopmall.bawei.user.login.contract.LoginImpl;

public class LoginFragment<P extends LoginImpl,V extends LoginContract.ILoginView> extends BaseFragment<P,V> implements LoginContract.ILoginView {
    @Override
    protected int layoutId() {
        return 0;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initPresenter() {

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
        /**
         *
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
