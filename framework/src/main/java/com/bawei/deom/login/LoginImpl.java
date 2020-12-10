package com.bawei.deom.login;

import com.bawei.deom.ClassInterface;
import com.bawei.deom.view.LoadingPage;

import java.util.HashMap;

import bean.LoginBean;
import bean.RegisterBean;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
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
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        pView.loading();

                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                       pView.hideloading();
                    }
                })
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterBean registerBean) {
                        if (registerBean.getCode().equals("200")){
                            pView.onregister(registerBean);
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
    public void loginShow(String name, final String password) {
        HashMap<String,String> map=new HashMap<>();
        map.put("name",name);
        map.put("password",password);
        ClassInterface.getUserInterface().login(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {

                    }
                })

                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                })
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        if (loginBean.getCode().equals("200")){
                            pView.onlogin(loginBean);
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
