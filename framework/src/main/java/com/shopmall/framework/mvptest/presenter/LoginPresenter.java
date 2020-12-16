package com.shopmall.framework.mvptest.presenter;

import com.shopmall.framework.callback.ILogin;
import com.shopmall.framework.contract.Contract;
import com.shopmall.framework.mvptest.repository.LoginRepository;
import com.shopmall.framework.view.LoadingPage;

import java.util.HashMap;

public class LoginPresenter extends Contract.LoginConstartPresenter {

    public LoginPresenter(Contract.LoginConstartView loginConstartView) {
        super(loginConstartView);
    }

    @Override
    public void login(String loginUrl, HashMap<String, String> map, final LoadingPage loadingPage) {
        mRepository.login(loginUrl, map, loadingPage, new ILogin() {
            @Override
            public void Ok(Object... objects) {
                if (objects!=null){
                    mView.get().Success(objects);
                }
            }

            @Override
            public void No(String mag) {
                if (mag!=null){
                    loadingPage.showErrorPage(mag.split(",")[1]);
                    mView.get().Error(mag);
                }
            }
        });
    }

    @Override
    protected void createRepository() {
        mRepository = new LoginRepository();
    }
}
