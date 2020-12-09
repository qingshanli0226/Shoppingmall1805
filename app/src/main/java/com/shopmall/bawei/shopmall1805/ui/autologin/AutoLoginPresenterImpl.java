package com.shopmall.bawei.shopmall1805.service.autologin;

import com.example.net.Retrofitcreators;
import com.example.net.bean.AutoLoginBean;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AutoLoginPresenterImpl extends AutoLoginContract.AutoLoginPrensnter {
    @Override
    public void MyautologinPrensnter(String token) {
        HashMap<String, String> map = new HashMap<>();
        map.put("token",token);
        Retrofitcreators.getiNetPresetenterWork().autoLogin(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AutoLoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AutoLoginBean autoLoginBean) {
                        if (autoLoginBean.getCode().equals("200")){
                            iView.MyautologinView(autoLoginBean);
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
