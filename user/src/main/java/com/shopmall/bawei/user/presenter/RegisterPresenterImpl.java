package com.shopmall.bawei.user.presenter;

import com.shopmall.bawei.common.ExceptionUtil;
import com.shopmall.bawei.net.NetFunction;
import com.shopmall.bawei.net.RetroCreator;
import com.shopmall.bawei.net.ShopmallObserver;
import com.shopmall.bawei.net.mode.BaseBean;
import com.shopmall.bawei.user.contract.RegisterContract;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RegisterPresenterImpl extends RegisterContract.ReigsterPresenter {

    @Override
    public void register(String name, String password) {
        HashMap<String,String> params = new HashMap<>();
        params.put("name", name);
        params.put("password", password);

        RetroCreator.getShopmallApiServie().register(params)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<String>,String>())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iHttpView.showLoaing();
                    }
                })
                .subscribe(new ShopmallObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        iHttpView.onRegister(s);
                        iHttpView.hideLoading(true,null);
                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {
                        iHttpView.hideLoading(false,ExceptionUtil.getErrorBean(errorCode, errorMessage));
                    }
                });
    }
}
