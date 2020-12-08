package com.shopmall.framework.mvptest.repository;

import com.shopmall.framework.callback.IRegister;
import com.shopmall.framework.constart.Constart;
import com.shopmall.framework.mvptest.model.RegisterModel;
import com.shopmall.framework.view.LoadingPage;

import java.util.HashMap;

public class RegisterRepository extends Constart.RegisterConstartRepository {
    @Override
    public void register(String url, HashMap<String, String> map, LoadingPage loadingPage, IRegister iRegister) {
        mModel.register(url,map,loadingPage,iRegister);
    }

    @Override
    protected void createModel() {
        mModel = new RegisterModel();
    }
}
