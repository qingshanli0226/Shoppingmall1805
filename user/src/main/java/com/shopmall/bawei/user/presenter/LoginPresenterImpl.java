package com.shopmall.bawei.user.presenter;


import com.shopmall.bawei.common.ExceptionUtil;
import com.shopmall.bawei.net.NetFunction;
import com.shopmall.bawei.net.RetroCreator;
import com.shopmall.bawei.net.ShopmallObserver;
import com.shopmall.bawei.net.mode.BaseBean;
import com.shopmall.bawei.net.mode.LoginBean;
import com.shopmall.bawei.user.contract.LoginContract;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class LoginPresenterImpl extends LoginContract.LoginPresenter {


    @Override
    public void login(String userName, String password) {
        HashMap<String,String> params = new HashMap<>();
        params.put("name", userName);
        params.put("password", password);

        RetroCreator.getShopmallApiServie().login(params)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<LoginBean>, LoginBean>())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iHttpView.showLoaing();
                    }
                })
                .subscribe(new ShopmallObserver<LoginBean>() {
                    @Override
                    public void onNext(LoginBean loginBean) {
                        iHttpView.onLogin(loginBean);
                        iHttpView.hideLoading(true,null);

                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {
                        iHttpView.hideLoading(false,ExceptionUtil.getErrorBean(errorCode, errorMessage));
                    }
                });
    }
}
