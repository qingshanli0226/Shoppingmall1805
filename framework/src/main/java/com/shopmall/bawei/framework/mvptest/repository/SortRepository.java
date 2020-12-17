package com.shopmall.bawei.framework.mvptest.repository;

import com.shopmall.bawei.framework.callback.Sort;
import com.shopmall.bawei.framework.constart.Constant;
import com.shopmall.bawei.framework.logingpage.LogingPage;
import com.shopmall.bawei.framework.mvptest.model.SortModel;

public class SortRepository extends Constant.SortConstartRepository {

    @Override
    protected void createModel() {
     mModel=new SortModel();
    }

    @Override
    public void Sort(String url, LogingPage logingPage, Sort sort) {
         mModel.Sort(url,logingPage,sort);
    }
}
