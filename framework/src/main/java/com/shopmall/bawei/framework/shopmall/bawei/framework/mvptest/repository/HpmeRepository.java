package com.shopmall.bawei.framework.shopmall.bawei.framework.mvptest.repository;


import com.shopmall.bawei.framework.shopmall.bawei.framework.callback.Home;
import com.shopmall.bawei.framework.shopmall.bawei.framework.constart.Constart;
import com.shopmall.bawei.framework.shopmall.bawei.framework.mvptest.model.HomeModel;

public class HpmeRepository extends Constart.HomeConstartRepository{


    @Override
    protected void createModel() {
        mModel=new HomeModel();
    }

    @Override
    public void homec(String url, Home home) {
        mModel.homec(url,home);

    }
}
