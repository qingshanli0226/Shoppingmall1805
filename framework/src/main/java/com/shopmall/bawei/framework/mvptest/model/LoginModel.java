package com.shopmall.bawei.framework.mvptest.model;


import com.shopmall.bawei.framework.callback.ILogin;
import com.shopmall.bawei.framework.constart.Constart;
import com.shopmall.bawei.net.Https;
import com.shopmall.bawei.net.HttpsFactory;
import com.shopmall.bean.Loginbean;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginModel implements Constart.LoginConstartModel {

    @Override
    public void login(String url, HashMap<String, String> map, final ILogin iLogin) {


        Https getinstance = HttpsFactory.getHttpsFactory().getinstance(Https.class);
        getinstance.getlogin(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Loginbean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Loginbean loginbean) {
                              iLogin.Ok(loginbean);
                    }

                    @Override
                    public void onError(Throwable e) {
                             iLogin.No(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
