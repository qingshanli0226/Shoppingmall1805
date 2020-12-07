package com.shopmall.bawei.shopmall1805.presenter;

import com.example.net.ConfigUrl;
import com.example.net.HttpRetrofitManager;
import com.example.net.INetPresetenterWork;
import com.example.net.JavaBean;
import com.shopmall.bawei.shopmall1805.contract.ClassfiyContract;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ClassPresenter extends ClassfiyContract.ClassfiyPresenter {



    @Override
    public void getData1() {

        new HttpRetrofitManager()
                .getRetrofit(ConfigUrl.BASE_URL)
                .create(INetPresetenterWork.class)
                .tag()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showsloading();
                    }
                })
                .subscribe(new Observer<JavaBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JavaBean resultBean) {
                        if (iView!=null){
                            iView.hideloading();
                            iView.getViewData1(resultBean);
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
