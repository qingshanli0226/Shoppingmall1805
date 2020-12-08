package com.shopmall.framework.mvptest.repository;

import com.shopmall.framework.callback.ILogin;
import com.shopmall.framework.constart.Constart;
import com.shopmall.framework.mvptest.model.LoginModel;
import com.shopmall.framework.view.LoadingPage;

import java.util.HashMap;

public class LoginRepository extends Constart.LoginConstartRepository{

    @Override
    protected void createModel() {
        mModel = new LoginModel();
    }

    @Override
    public void login(String url, HashMap<String, String> map, LoadingPage loadingPage, ILogin iLogin) {
        mModel.login(url,map,loadingPage,iLogin);
    }
}
