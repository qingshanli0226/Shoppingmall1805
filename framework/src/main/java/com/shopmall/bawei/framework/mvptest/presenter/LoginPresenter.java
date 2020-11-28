package com.shopmall.bawei.framework.mvptest.presenter;


import com.shopmall.bawei.framework.callback.ILogin;
import com.shopmall.bawei.framework.constart.Constart;
import com.shopmall.bawei.framework.mvptest.repository.LoginRepository;

import java.util.HashMap;

public class LoginPresenter extends Constart.LoginConstartPresenter {


    public LoginPresenter(Constart.LoginConstartView loginConstartView) {
        super(loginConstartView);
    }

    @Override
    public void login(String url, HashMap<String, String> map) {
          mRepository.login(url, map, new ILogin() {
              @Override
              public void Ok(Object... objects) {

                    if (objects!=null){

                        mView.get().Success(objects);
                    }
              }

              @Override
              public void No(String mag) {
                  if (mag!=null){
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
