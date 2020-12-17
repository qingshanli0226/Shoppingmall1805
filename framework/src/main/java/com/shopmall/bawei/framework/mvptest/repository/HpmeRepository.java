package com.shopmall.bawei.framework.mvptest.repository;


import com.shopmall.bawei.framework.callback.Home;
import com.shopmall.bawei.framework.constart.Constant;
import com.shopmall.bawei.framework.logingpage.LogingPage;
import com.shopmall.bawei.framework.mvptest.model.HomeModel;

public class HpmeRepository extends Constant.HomeConstartRepository{


    @Override
    protected void createModel() {
        mModel=new HomeModel();
    }

    @Override
    public void homec(String url, LogingPage logingPage, Home home) {
        mModel.homec(url,logingPage,home);

    }
}
