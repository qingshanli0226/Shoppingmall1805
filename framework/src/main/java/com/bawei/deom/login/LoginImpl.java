package com.bawei.deom.login;

import com.bawei.deom.ClassInterface;

import java.util.HashMap;

import bean.LoginBean;
import bean.RegisterBean;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginImpl extends LoginCountroller.LoginShow {
    @Override
    public void registerShow(String name, String password) {
        HashMap<String,String> map=new HashMap<>();
        map.put("name",name);
        map.put("password",password);
        ClassInterface.getUserInterface().register(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterBean registerBean) {
                                   pView.register(registerBean);
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
    public void loginShow(String name, String password) {
        HashMap<String,String> map=new HashMap<>();
        map.put("name",name);
        map.put("password",password);
        ClassInterface.getUserInterface().login(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        pView.login(loginBean);
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
