package com.shopmall.bawei.shopmall1805.classification.prsenter;

import com.example.common2.TagBean;
import com.shopmall.bawei.shopmall1805.classification.contract.LabelContract;


import http.MyHttp;
import http.ShopmallObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LabelPresenter extends LabelContract.LabelPresenter {

    @Override
    public void getILabelData() {
        MyHttp.getShopmallApiService()
                .gettag()
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
                        iHttpView.hideLoading();
                    }
                })
                .subscribe(new ShopmallObserver<TagBean>() {
                    @Override
                    public void onNext(TagBean tagBean) {
                        iHttpView.onLabel(tagBean);
                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {
                        iHttpView.showError(errorCode,errorMessage);
                    }
                });
    }
}