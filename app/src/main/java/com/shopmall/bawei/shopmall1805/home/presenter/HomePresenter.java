package com.shopmall.bawei.shopmall1805.home.presenter;

import com.bw.net.RetraficCreator;
import com.bw.net.bean.HomeFragmentBean;
import com.shopmall.bawei.shopmall1805.home.contract.HomeContract;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter extends HomeContract.HomePresenter {
    @Override
    public void onGetHomeData() {
        RetraficCreator.getiNetWorkApiService().getHomeData()
                .delay(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeFragmentBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeFragmentBean homeFragmentBean) {
                        if (homeFragmentBean.getCode() == 200){
                            iView.onOk(homeFragmentBean);
                        }
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
