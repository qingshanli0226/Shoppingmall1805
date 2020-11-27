package com.shopmall.bawei.framework.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import com.shopmall.bawei.framework.ShopUserManager;
import com.shopmall.bawei.net.NetFunction;
import com.shopmall.bawei.net.RetroCreator;
import com.shopmall.bawei.net.mode.BaseBean;
import com.shopmall.bawei.net.mode.LoginBean;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class ShopmallService extends Service {

    public class KsBinder extends Binder {
        public ShopmallService getService() {
            return ShopmallService.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        autoLogin(ShopUserManager.getInstance().getToken());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new KsBinder();
    }


    public void autoLogin(String tokenValue) {
        HashMap<String,String> params = new HashMap<>();
        params.put("token", tokenValue);
        RetroCreator.getShopmallApiServie().autoLogin(params)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<LoginBean>,LoginBean>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        ShopUserManager.getInstance().saveLoginBean(loginBean);
                        Log.d("LQS", "登录成功...............");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("LQS", "onError");

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
