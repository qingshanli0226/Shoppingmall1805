package com.shopmall.bawei.framework.mvptest.model;


import android.util.Log;

import com.shopmall.bawei.framework.callback.Home;
import com.shopmall.bawei.framework.constart.Constart;
import com.shopmall.bawei.net.Https;
import com.shopmall.bawei.net.HttpsFactory;
import com.shopmall.bean.HomeData;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeModel implements Constart.HomeConstartModel {

    @Override
    public void homec(String url, final Home home) {
        Https getinstance = HttpsFactory.getHttpsFactory().getinstance(Https.class);
        getinstance.getonser(url)
                 .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<HomeData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeData homeData) {
                        Log.e("wk",""+homeData);
                            home.Ok(homeData);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("wk",""+e.getMessage());
                           home.No(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
