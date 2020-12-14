package com.shopmall.bawei.shopmall1805.ui.activity.presenter;

import com.example.net.Retrofitcreators;
import com.example.net.bean.BaseBean;
import com.shopmall.bawei.shopmall1805.ui.activity.contract.BindContract;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class BindPresenter extends BindContract.IBindPresenter {
    @Override
    public void BindPhone(String phone) {
        HashMap<String,String> map = new HashMap<>();
        map.put("phone",phone);
        Retrofitcreators.getiNetPresetenterWork().updatePhone(map)
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
                            iView.onPhoneResult(stringBaseBean.getResult());
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

    @Override
    public void BindAdress(String adress) {
        HashMap<String,String> map = new HashMap<>();
        map.put("address",adress);
        Retrofitcreators.getiNetPresetenterWork().updateAddress(map)
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
                            iView.onAdressResult(stringBaseBean.getResult());
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
