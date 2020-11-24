package com.shopmall.framework.mvptest.model;

import com.shopmall.framework.callback.Sort;
import com.shopmall.framework.constart.Constart;
import com.shopmall.net.Https;
import com.shopmall.net.RetrofitFactory;
import com.shopmall.net.bean.SortData;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SortModel implements Constart.SortConstartModel {

    @Override
    public void Sort(String url, final Sort sort) {
        Https getinstance = RetrofitFactory.getInstance().create(Https.class);
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

    @Override
    public void destroy() {

    }
}
