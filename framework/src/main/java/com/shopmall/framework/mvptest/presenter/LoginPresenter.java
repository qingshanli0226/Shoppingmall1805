package com.shopmall.framework.mvptest.presenter;

import com.shopmall.framework.constart.Constart;

public class LoginPresenter extends Constart.LoginConstartPresenter {

    public LoginPresenter(Constart.LoginConstartView loginConstartView) {
        super(loginConstartView);
    }

    @Override
    protected void createRepository() {

    }
}
