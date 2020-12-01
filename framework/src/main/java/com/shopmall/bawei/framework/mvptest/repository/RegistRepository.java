package com.shopmall.bawei.framework.mvptest.repository;

import com.shopmall.bawei.framework.callback.IRegist;
import com.shopmall.bawei.framework.constart.Constart;
import com.shopmall.bawei.framework.logingpage.LogingPage;
import com.shopmall.bawei.framework.mvptest.model.RegistModel;

import java.util.HashMap;

public class RegistRepository extends Constart.RegistConstartRepository {
    @Override
    public void regist(String url, HashMap<String, String> map, LogingPage logingPage, IRegist iRegist) {
          mModel.regist(url,map,logingPage,iRegist);
    }

    @Override
    protected void createModel() {
          mModel=new RegistModel();
    }
}
