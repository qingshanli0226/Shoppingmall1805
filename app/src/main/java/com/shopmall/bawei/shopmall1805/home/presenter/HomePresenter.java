package com.shopmall.bawei.shopmall1805.home.presenter;

import com.bw.common.view.LoadingPage;
import com.bw.net.RetraficCreator;
import com.bw.net.bean.HomeFragmentBean;
import com.shopmall.bawei.shopmall1805.home.contract.HomeContract;


import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter extends HomeContract.HomePresenter {
    @Override
    public void onGetHomeData() {
        RetraficCreator.getiNetWorkApiService().getHomeData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showsLoaing();
                    }
                })
                .subscribe(new Observer<HomeFragmentBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeFragmentBean homeFragmentBean) {
                        if (homeFragmentBean.getCode() == 200){
                            iView.onOk(homeFragmentBean);
                            iView.hidesLoading(true);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
