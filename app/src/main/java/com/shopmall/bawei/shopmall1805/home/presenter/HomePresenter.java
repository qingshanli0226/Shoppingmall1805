package com.shopmall.bawei.shopmall1805.home.presenter;

import android.util.Log;

import com.example.net.Retrofitcreators;
import com.example.net.bean.BaseBean;
import com.example.net.bean.HomeBean;
import com.shopmall.bawei.shopmall1805.home.contract.HomeContract;


import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter extends HomeContract.SlerakPresenter {
    @Override
    public void getskerak() {
        Retrofitcreators.getiNetPresetenterWork().home()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showsloading();
                    }
                })
                .subscribe(new Observer<BaseBean<HomeBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean<HomeBean> homeBeanBaseBean) {
                        HomeBean result = homeBeanBaseBean.getResult();
                        if (iView!=null){
                            iView.hideloading();
                            iView.onskerk(result);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("zld","onError");
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
