package com.shopmall.bawei.framework.mvptest.presenter;

import com.shopmall.bawei.framework.callback.IRegist;
import com.shopmall.bawei.framework.constart.Constant;
import com.shopmall.bawei.framework.logingpage.LogingPage;
import com.shopmall.bawei.framework.mvptest.repository.RegistRepository;
import com.shopmall.bean.Registbean;

import java.util.HashMap;

public class RegistPresenter extends Constant.RegistConstartPresenter {
    public RegistPresenter(Constant.RegistConstartView registConstartView) {
        super(registConstartView);
    }

    @Override
    public void regist(String url, HashMap<String, String> map, final LogingPage logingPage) {
        mRepository.regist(url, map,logingPage, new IRegist() {
            @Override
            public void Ok(Object... objects) {
                  if (objects!=null){
                      Registbean registbean=(Registbean)objects[0];
                      mView.get().Success(registbean.getMessage());
                  }
            }

            @Override
            public void No(String mag) {
                if (mag!=null){
                    logingPage.showError(mag.split(",")[1]);
                    mView.get().Error(mag);
                }
            }
        });
    }

    @Override
    protected void createRepository() {
           mRepository=new RegistRepository();
    }
}
