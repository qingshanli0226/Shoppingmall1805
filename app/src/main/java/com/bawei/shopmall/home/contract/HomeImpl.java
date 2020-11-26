package com.bawei.shopmall.home.contract;

import android.util.Log;

import com.bawei.net.OkHttpHelper;
import com.bawei.net.mode.HomeBean;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomeImpl extends HomeContract.HomePresenter {
    @Override
    public void getHomeData() {
        OkHttpHelper.getApi().getHomeData()
                .delay(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showLoading();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        iView.hideLoading();
                    }
                })
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeBean homeBean) {
                        Log.i("TAG", "onNext: "+homeBean);
                        iView.onHomeData(homeBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iView.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
