package com.shopmall.framework.mvptest.repository;

import com.shopmall.framework.callback.Sort;
import com.shopmall.framework.constart.Constart;
import com.shopmall.framework.mvptest.model.SortModel;

public class SortRepository extends Constart.SortConstartRepository {

    @Override
    protected void createModel() {
     mModel=new SortModel();
    }

    @Override
    public void Sort(String url, Sort sort) {
         mModel.Sort(url,sort);
    }
}
