package com.shopmall.framework.mvptest.model;

import com.shopmall.framework.callback.IRegister;
import com.shopmall.framework.constart.Constart;
import com.shopmall.framework.view.LoadingPage;
import com.shopmall.net.Https;
import com.shopmall.net.RetrofitFactory;
import com.shopmall.net.bean.RegisterBean;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RegisterModel implements Constart.RegisterConstartModel {
    @Override
    public void register(String url, HashMap<String, String> map, final LoadingPage loadingPage, final IRegister iRegister) {
        Https instance = RetrofitFactory.getInstance().create(Https.class);
        instance.getRegister(url,map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        loadingPage.showLoadingPage();
                    }
                })
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterBean registerBean) {
                        loadingPage.showSuccessView();
                        iRegister.Ok(registerBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadingPage.showSuccessView();
                        iRegister.No(e.getMessage());
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
