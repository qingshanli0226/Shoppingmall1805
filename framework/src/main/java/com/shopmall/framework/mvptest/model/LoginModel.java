package com.shopmall.framework.mvptest.model;

import android.util.Log;

import com.shopmall.framework.callback.ILogin;
import com.shopmall.framework.constart.Constart;
import com.shopmall.framework.view.LoadingPage;
import com.shopmall.net.Https;
import com.shopmall.net.RetrofitFactory;
import com.shopmall.net.bean.LoginBean;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginModel implements Constart.LoginConstartModel {

    @Override
    public void login(String url, final HashMap<String, String> map, final LoadingPage loadingPage, final ILogin iLogin) {
        Log.i("TAG", "onError: "+map.get("name"));
        Https instance = RetrofitFactory.getInstance().create(Https.class);
        instance.getLogin(url,map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        loadingPage.showLoadingPage();
                    }
                })
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        loadingPage.showSuccessView();
                        iLogin.Ok(loginBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                        loadingPage.showSuccessView();
                        iLogin.No(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void destroy() {

    }

}
