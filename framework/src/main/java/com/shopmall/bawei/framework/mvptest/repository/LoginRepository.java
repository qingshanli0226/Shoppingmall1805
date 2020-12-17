package com.shopmall.bawei.framework.mvptest.repository;


import com.shopmall.bawei.framework.callback.ILogin;
import com.shopmall.bawei.framework.constart.Constant;
import com.shopmall.bawei.framework.logingpage.LogingPage;
import com.shopmall.bawei.framework.mvptest.model.LoginModel;

import java.util.HashMap;

public class LoginRepository extends Constant.LoginConstartRepository{


    @Override
    protected void createModel() {
         mModel=new LoginModel();
    }

    @Override
    public void login(String url, HashMap<String, String> map, LogingPage logingPage, ILogin iLogin) {
         mModel.login(url,map,logingPage,iLogin);
    }
}
