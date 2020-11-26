package com.shopmall.bawei.user.login;

import com.example.net.RetrofitCreater;
import com.example.net.bean.LoginBean;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenterImpl extends LoginContract.LoginPresenter {
    @Override
    public void login(String name, String pwd) {
        HashMap<String, String> map = new HashMap<>();
        map.put("name",name);
        map.put("password",pwd);
        RetrofitCreater.getiNetWorkApi().login(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean bean) {
                        iview.onOk(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iview.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
