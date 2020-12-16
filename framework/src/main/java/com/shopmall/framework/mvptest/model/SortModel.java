package com.shopmall.framework.mvptest.model;

import com.shopmall.framework.callback.Sort;
import com.shopmall.framework.contract.Contract;
import com.shopmall.framework.view.LoadingPage;
import com.shopmall.net.Https;
import com.shopmall.net.RetrofitFactory;
import com.shopmall.net.bean.SortData;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SortModel implements Contract.SortConstartModel {

    @Override
    public void Sort(String url, final LoadingPage loadingPage, final Sort sort) {
        Https getinstance = RetrofitFactory.getInstance().create(Https.class);
        getinstance.getSort(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        loadingPage.showLoadingPage();
                    }
                })
                .subscribe(new Observer<SortData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SortData sortData) {
                        loadingPage.showSuccessView();
                        sort.Ok(sortData);
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadingPage.showSuccessView();
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
