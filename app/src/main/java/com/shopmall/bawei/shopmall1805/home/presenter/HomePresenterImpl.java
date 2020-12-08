package com.shopmall.bawei.shopmall1805.home.presenter;

import android.util.Log;

import com.example.common2.HomeBean;
import com.shopmall.bawei.shopmall1805.home.comtract.HomeContract;

import http.BaseBean;
import http.MyHttp;
import http.NetFunction;
import http.ShopmallObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomePresenterImpl extends HomeContract.HomePresenter {

    @Override
    public void getHomeData() {
        MyHttp.getShopmallApiService()
                .gethome()
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
                .subscribe(new ShopmallObserver<BaseBean<HomeBean>>() {
                    @Override
                    public void onNext(BaseBean<HomeBean> homeBeanBaseBean) {
                        iHttpView.onHomeData(homeBeanBaseBean.getResult());
                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {
                        iHttpView.showError(errorCode,errorMessage);
                    }
                });
    }
}
