package com.shopmall.bawei.shopmall1805.classification.prsenter;

import android.util.Log;

import com.example.common2.SkirstBean;
import com.shopmall.bawei.shopmall1805.classification.contract.ClassificationContract;


import http.BaseBean;
import http.MyHttp;
import http.ShopmallObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ClassificationPresenter extends ClassificationContract.ClassificationPresenter {



    @Override
    public void getIClassificationData(String url) {
        Log.i("getIClassificationData", "getIClassificationData: ");
        MyHttp.getShopmallApiService()
                .getskirt(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iHttpView.showLoaing();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.i("TAG1", "run: ");
                        iHttpView.hideLoading();
                    }
                })
                .subscribe(new ShopmallObserver<BaseBean<SkirstBean>>() {
                    @Override
                    public void onNext(BaseBean<SkirstBean> skirstBeanBaseBean) {
                        Log.i("TAG2", "run: ");
                        iHttpView.onClassification(skirstBeanBaseBean.getResult());
                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {
                        Log.i("TAG3", "run: ");
                        iHttpView.showError(errorCode,errorMessage);
                    }
                });
    }
}