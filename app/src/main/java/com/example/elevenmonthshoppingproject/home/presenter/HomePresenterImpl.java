package com.example.elevenmonthshoppingproject.home.presenter;

import android.util.Log;

import com.example.elevenmonthshoppingproject.home.contract.HomeContract;
import com.example.net.NetFunction;
import com.example.net.RetorfitCreate;
import com.example.net.ShopMallObserver;
import com.example.net.bean.BaseBean;
import com.example.net.bean.HomeBean;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomePresenterImpl extends HomeContract.HomePresenter {
    @Override
    public void getHomeData() {
        RetorfitCreate.getiNetworkserviceimpl().recommondebean()
                .delay(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<HomeBean>,HomeBean>())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iview.showLoading();//显示加载的UI
                    }
                })
                .subscribe(new ShopMallObserver<HomeBean>() {
                    @Override
                    public void onNext(HomeBean homeBean) {
                        Log.i("---",""+homeBean);
                            iview.onHomeData(homeBean);
                            iview.hideLoading(true,null);
                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {
                            iview.onError(errorCode,errorMessage);
                            iview.hideLoading(false,errorMessage);
                    }
                });
    }
}
