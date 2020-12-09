package com.bawei.deom.autologin;

import android.os.UserManager;

import com.bawei.deom.ClassInterface;

import java.util.HashMap;

import bean.AutoLoginBeen;
import bean.LoginBean;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AutologinImpl extends AutologinCountroller.AutoLoginShow {
    @Override
    public void MyautologinShow(String token) {

        HashMap<String,String> map=new HashMap<>();
        map.put("token", token);
        ClassInterface.getUserInterface().autoLogin(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean autoLoginBeen) {
                        if (autoLoginBeen.getCode().equals("200")){
                            pView.MyautologinView(autoLoginBeen);
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
