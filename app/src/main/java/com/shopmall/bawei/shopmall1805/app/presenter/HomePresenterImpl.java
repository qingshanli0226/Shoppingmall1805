package com.shopmall.bawei.shopmall1805.app.presenter;

import com.shopmall.bawei.shopmall1805.app.contract.HomeContract;
import com.shopmall.bawei.shopmall1805.common.HomeBean;
import com.shopmall.bawei.shopmall1805.net.BaseObserver;
import com.shopmall.bawei.shopmall1805.net.RetrofitUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomePresenterImpl extends HomeContract.HomePresenter {

    @Override
    public void getHomeData() {
        RetrofitUtils.getiNetPresetenterWork()
                .home()
                .delay(3,TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iHttpView.showLoaing();
                    }
                })
                .subscribe(new BaseObserver<HomeBean>() {
                    @Override
                    public void onNext(HomeBean homeBean) {
                        if(homeBean!=null){
                            iHttpView.onHomeDatas(homeBean);
                            iHttpView.hideLoading();
                        }
                    }
                });
    }
}
