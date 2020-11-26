package com.shopmall.bawei.user.register.view;

import android.widget.Toast;

import com.shopmall.bawei.common.ErrorBean;
import com.shopmall.bawei.framework.BaseFragment;
import com.shopmall.bawei.user.register.contract.RegisterContract;
import com.shopmall.bawei.user.register.contract.RegisterImpl;

public class RegisterFragment<P extends RegisterImpl,V extends RegisterContract.IRegisterView> extends BaseFragment<P,V> implements RegisterContract.IRegisterView {
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
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {
        hideLoadingPage(isSuccess,errorBean);
    }

    @Override
    public void showLoaDing() {
        showLoading();
    }

    @Override
    public void showEmpty() {

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
