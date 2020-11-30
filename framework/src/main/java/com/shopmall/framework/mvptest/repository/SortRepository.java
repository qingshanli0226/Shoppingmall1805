package com.shopmall.framework.mvptest.repository;

import com.shopmall.framework.callback.Sort;
import com.shopmall.framework.constart.Constart;
import com.shopmall.framework.mvptest.model.SortModel;
import com.shopmall.framework.view.LoadingPage;

public class SortRepository extends Constart.SortConstartRepository {

    @Override
    protected void createModel() {
     mModel=new SortModel();
    }

    @Override
    public void Sort(String url, LoadingPage loadingPage, Sort sort) {
         mModel.Sort(url,loadingPage,sort);
    }
}
