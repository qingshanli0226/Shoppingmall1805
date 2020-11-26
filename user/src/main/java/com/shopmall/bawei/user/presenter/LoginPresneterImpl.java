package com.shopmall.bawei.user.presenter;

import com.shopmall.bawei.net.NetFunction;
import com.shopmall.bawei.net.OkHttpHelper;
import com.shopmall.bawei.net.mode.BaseBean;
import com.shopmall.bawei.net.mode.LoginBean;
import com.shopmall.bawei.user.contract.LoginContract;

import java.util.HashMap;
import java.util.jar.Manifest;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class LoginPresneterImpl extends LoginContract.LoginPresenter {
    @Override
    public void login(String userName, String password) {
        HashMap<String,String> params = new HashMap<>();
        params.put("name", userName);
        params.put("password", password);

        OkHttpHelper.getApi().login(params)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<LoginBean>,LoginBean>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        iView.onLogin(loginBean);
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
