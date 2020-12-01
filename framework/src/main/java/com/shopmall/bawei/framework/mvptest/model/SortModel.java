package com.shopmall.bawei.framework.mvptest.model;

import com.shopmall.bawei.framework.callback.Sort;
import com.shopmall.bawei.framework.constart.Constart;
import com.shopmall.bawei.framework.logingpage.LogingPage;
import com.shopmall.bawei.net.Https;
import com.shopmall.bawei.net.HttpsFactory;
import com.shopmall.bean.SortData;
import com.shopmall.error.GetError;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SortModel implements Constart.SortConstartModel {

    @Override
    public void Sort(String url, final LogingPage logingPage, final Sort sort) {
        Https getinstance = HttpsFactory.getHttpsFactory().getinstance(Https.class);
        getinstance.getsort(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        logingPage.loadingPage();
                    }
                })
                .subscribe(new Observer<SortData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SortData sortData) {
                        logingPage.showsucessPage();
                          sort.Ok(sortData);
                    }

                    @Override
                    public void onError(Throwable e) {
                        logingPage.showsucessPage();
                        String errorBean = GetError.getErrorBean(e);
                        sort.No(errorBean);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
