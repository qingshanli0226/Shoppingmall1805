package com.shopmall.bawei.framework.mvptest.model;

import com.shopmall.bawei.framework.callback.Sort;
import com.shopmall.bawei.framework.constart.Constart;
import com.shopmall.bawei.net.Https;
import com.shopmall.bawei.net.HttpsFactory;
import com.shopmall.bean.SortData;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SortModel implements Constart.SortConstartModel {

    @Override
    public void Sort(String url, final Sort sort) {
        Https getinstance = HttpsFactory.getHttpsFactory().getinstance(Https.class);
        getinstance.getsort(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SortData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SortData sortData) {
                          sort.Ok(sortData);
                    }

                    @Override
                    public void onError(Throwable e) {
                        sort.No(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
