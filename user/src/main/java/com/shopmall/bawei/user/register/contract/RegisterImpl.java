package com.shopmall.bawei.user.register.contract;

import com.shopmall.bawei.common.ErrorBean;
import com.shopmall.bawei.common.ExceptionUtil;
import com.shopmall.bawei.net.NetFunction;
import com.shopmall.bawei.net.OkHttpHelper;
import com.shopmall.bawei.net.mode.BaseBean;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RegisterImpl extends RegisterContract.IRegisterPresenter {
    @Override
    public void register(String name, String password) {
        HashMap<String, String> map = new HashMap<>();
        map.put("name",name);
        map.put("password",password);
        OkHttpHelper.getApi()
                .register(map)
                .delay(3, TimeUnit.SECONDS)
                .map(new NetFunction<BaseBean<String>,String>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showLoaDing();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String registerBean) {
                        if(registerBean == null){
                            iView.showEmpty();
                        }else{
                            iView.onRegister(registerBean);
                            iView.hideLoading(true,null);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ErrorBean errorBean = ExceptionUtil.getErrorBean(e);
                        iView.hideLoading(false, errorBean);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
