package com.shopmall.bawei.shopmall1805.Classification.prsenter;

import android.util.Log;

import com.example.common2.SkirstBean;
import com.shopmall.bawei.shopmall1805.Classification.contract.ClassificationContract;

import java.util.List;


import http.BaseBean;
import http.MyHttp;
import http.NetFunction;
import http.ShopmallObserver;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import mvp.view.BasePresenter;

public class ClassificationPresenter extends ClassificationContract.ClassificationPresenter {



    @Override
    public void getIClassificationData(String url) {

        MyHttp.getShopmallApiService()
                .getskirt(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ShopmallObserver<BaseBean<SkirstBean>>() {
                    @Override
                    public void onNext(BaseBean<SkirstBean> skirstBeanBaseBean) {

                        iHttpView.onClassification(skirstBeanBaseBean.getResult());
                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {
                        iHttpView.showError(errorCode,errorMessage);
                    }
                });
    }
}