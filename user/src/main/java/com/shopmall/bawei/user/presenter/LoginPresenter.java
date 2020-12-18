package com.shopmall.bawei.user.presenter;

import android.util.Log;

import com.example.net.Retrofitcreators;
import com.example.net.bean.BaseBean;
import com.example.net.bean.LoginBean;
import com.shopmall.bawei.user.contract.LoginContact;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter extends LoginContact.LoginPresenter {
    @Override
    public void getlogin(String name, String password) {
        HashMap<String,String> map = new HashMap<>();
        map.put("name",name);
        map.put("password",password);
        Retrofitcreators.getiNetPresetenterWork().login(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showsloading();
                    }
                })
                .subscribe(new Observer<BaseBean<LoginBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean<LoginBean> loginBeanBaseBean) {
                        iView.hideloading();
                        Log.i("---", "onNext: login："+loginBeanBaseBean.getResult().getToken());
                            if (loginBeanBaseBean.getCode().equals("200")){
                                iView.onlogin(loginBeanBaseBean.getResult());
                            }else {
                                iView.onErroy(loginBeanBaseBean.getMessage());
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
