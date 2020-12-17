package com.shopmall.bawei.framework.mvptest.presenter;


import com.shopmall.bawei.framework.callback.ILogin;
import com.shopmall.bawei.framework.constart.Constant;
import com.shopmall.bawei.framework.logingpage.LogingPage;
import com.shopmall.bawei.framework.mvptest.repository.LoginRepository;

import java.util.HashMap;

public class LoginPresenter extends Constant.LoginConstartPresenter {


    public LoginPresenter(Constant.LoginConstartView loginConstartView) {
        super(loginConstartView);
    }

    @Override
    public void login(String url, HashMap<String, String> map, final LogingPage logingPage) {
          mRepository.login(url, map,logingPage, new ILogin() {
              @Override
              public void Ok(Object... objects) {

                    if (objects!=null){

                        mView.get().Success(objects);
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
        mRepository=new LoginRepository();
    }
}
