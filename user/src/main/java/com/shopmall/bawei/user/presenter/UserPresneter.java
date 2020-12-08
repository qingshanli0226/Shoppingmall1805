package com.shopmall.bawei.user.presenter;

import com.example.common2.LoginBean;
import com.example.common2.RegisterBean;
import com.shopmall.bawei.user.contract.UserContract;

import java.util.HashMap;

import http.BaseBean;
import http.MyHttp;
import http.NetFunction;
import http.ShopmallObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserPresneter extends UserContract.UserPresenter {

    @Override
    public void getILoginBean(String name, String password) {
        HashMap<String,String> params = new HashMap<>();
        params.put("name", name);
        params.put("password", password);
        MyHttp.getShopmallApiService().login(params)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<LoginBean>,LoginBean>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ShopmallObserver<LoginBean>() {
                    @Override
                    public void onNext(LoginBean loginBean) {
                        iHttpView.onLogin(loginBean);
                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {
                        iHttpView.showError(errorCode,errorMessage);
                    }
                });
    }

    @Override
    public void getIRegisterBean(String name, String password) {
        HashMap<String,String> params = new HashMap<>();
        params.put("name", name);
        params.put("password", password);
        MyHttp.getShopmallApiService().register(params)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<RegisterBean>,RegisterBean>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ShopmallObserver<RegisterBean>() {
                    @Override
                    public void onNext(RegisterBean registerBean) {
                        iHttpView.onRegister(registerBean);
                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {
                        iHttpView.showError(errorCode,errorMessage);
                    }
                });
    }
}
