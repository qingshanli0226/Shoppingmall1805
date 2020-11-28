package com.shopmall.bawei.framework.mvptest.model;


import android.util.Log;

import com.shopmall.bawei.framework.callback.Home;
import com.shopmall.bawei.framework.constart.Constart;
import com.shopmall.bawei.framework.logingpage.LogingPage;
import com.shopmall.bawei.net.Https;
import com.shopmall.bawei.net.HttpsFactory;
import com.shopmall.bean.HomeData;
import com.shopmall.error.GetError;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomeModel implements Constart.HomeConstartModel {

    @Override
    public void homec(String url, final LogingPage logingPage, final Home home) {
        Https getinstance = HttpsFactory.getHttpsFactory().getinstance(Https.class);
        getinstance.getonser(url)
                 .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        logingPage.loadingPage();
                    }
                })
                .subscribe(new Observer<HomeData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeData homeData) {
                        logingPage.showsucessPage();
                        Log.e("wk",""+homeData);
                            home.Ok(homeData);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("wk",""+e.getMessage());
                           logingPage.showsucessPage();
                        String errorBean = GetError.getErrorBean(e);
                        home.No(errorBean);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
