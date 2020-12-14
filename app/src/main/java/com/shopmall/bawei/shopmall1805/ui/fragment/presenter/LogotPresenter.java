package com.shopmall.bawei.shopmall1805.ui.fragment.presenter;

import com.example.net.Retrofitcreators;
import com.example.net.bean.BaseBean;
import com.shopmall.bawei.shopmall1805.ui.fragment.contract.LogotContract;
import com.shopmall.bawei.user.contract.LoginContact;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LogotPresenter extends LogotContract.ILogotPresenter {

    @Override
    public void logotUser() {
        Retrofitcreators.getiNetPresetenterWork().logotlogin()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showsloading();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean<String> stringBaseBean) {
                        if (stringBaseBean.getCode().equals("200")){
                            iView.hideloading();
                            iView.onLogotSucess(stringBaseBean.getResult());
                        }else {
                            iView.hideloading();
                            iView.onErroy(stringBaseBean.getMessage());
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
