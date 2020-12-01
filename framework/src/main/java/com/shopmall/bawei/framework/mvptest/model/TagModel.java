package com.shopmall.bawei.framework.mvptest.model;

import com.shopmall.bawei.framework.callback.Tag;
import com.shopmall.bawei.framework.constart.Constart;
import com.shopmall.bawei.framework.logingpage.LogingPage;
import com.shopmall.bawei.net.Https;
import com.shopmall.bawei.net.HttpsFactory;
import com.shopmall.bean.TagData;
import com.shopmall.error.GetError;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class TagModel implements Constart.TagConstartModel {
    @Override
    public void Tagdata(String url, final LogingPage logingPage, final Tag sort) {
        Https getinstance = HttpsFactory.getHttpsFactory().getinstance(Https.class);
        getinstance.getTag(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        logingPage.loadingPage();
                    }
                })
                .subscribe(new Observer<TagData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TagData tagData) {
                        logingPage.showsucessPage();
                          sort.Ok(tagData);
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
