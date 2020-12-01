package com.shopmall.bawei.framework.mvptest.model;


import com.shopmall.bawei.framework.callback.ILogin;
import com.shopmall.bawei.framework.constart.Constart;
import com.shopmall.bawei.framework.logingpage.LogingPage;
import com.shopmall.bawei.net.Https;
import com.shopmall.bawei.net.HttpsFactory;
import com.shopmall.bean.Loginbean;
import com.shopmall.error.GetError;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginModel implements Constart.LoginConstartModel {

    @Override
    public void login(String url, HashMap<String, String> map, final LogingPage logingPage, final ILogin iLogin) {


        Https getinstance = HttpsFactory.getHttpsFactory().getinstance(Https.class);
        getinstance.getlogin(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        logingPage.loadingPage();
                    }
                })
                .subscribe(new Observer<Loginbean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Loginbean loginbean) {
                              logingPage.showsucessPage();
                              iLogin.Ok(loginbean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        logingPage.showsucessPage();
                        String errorBean = GetError.getErrorBean(e);
                        iLogin.No(errorBean);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
