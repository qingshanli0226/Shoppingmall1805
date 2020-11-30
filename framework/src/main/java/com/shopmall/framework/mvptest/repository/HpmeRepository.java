package com.shopmall.framework.mvptest.repository;

import com.shopmall.framework.callback.Home;
import com.shopmall.framework.constart.Constart;
import com.shopmall.framework.mvptest.model.HomeModel;
import com.shopmall.framework.view.LoadingPage;

public class HpmeRepository extends Constart.HomeConstartRepository{

    @Override
    protected void createModel() {
        mModel=new HomeModel();
    }

    @Override
    public void homec(String url, LoadingPage loadingPage, Home home) {
        mModel.homec(url,loadingPage,home);
    }
}
