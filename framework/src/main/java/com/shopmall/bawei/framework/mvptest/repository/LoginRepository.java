package com.shopmall.bawei.framework.mvptest.repository;


import com.shopmall.bawei.framework.callback.ILogin;
import com.shopmall.bawei.framework.constart.Constart;
import com.shopmall.bawei.framework.mvptest.model.LoginModel;

import java.util.HashMap;

public class LoginRepository extends Constart.LoginConstartRepository{


    @Override
    protected void createModel() {
         mModel=new LoginModel();
    }

    @Override
    public void login(String url, HashMap<String, String> map, ILogin iLogin) {
         mModel.login(url,map,iLogin);
    }
}
