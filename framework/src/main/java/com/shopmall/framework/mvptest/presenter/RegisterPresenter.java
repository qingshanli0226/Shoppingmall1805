package com.shopmall.framework.mvptest.presenter;

import com.shopmall.framework.callback.IRegister;
import com.shopmall.framework.contract.Contract;
import com.shopmall.framework.mvptest.repository.RegisterRepository;
import com.shopmall.framework.view.LoadingPage;

import java.util.HashMap;

public class RegisterPresenter extends Contract.RegisterConstartPresenter {
    public RegisterPresenter(Contract.RegisterConstartView registerConstartView) {
        super(registerConstartView);
    }

    @Override
    public void register(String loginUrl, HashMap<String, String> map, final LoadingPage loadingPage) {
        mRepository.register(loginUrl, map, loadingPage, new IRegister() {
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
        mRepository = new RegisterRepository();
    }
}
