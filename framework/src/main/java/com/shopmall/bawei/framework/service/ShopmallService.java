package com.shopmall.bawei.framework.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.shopmall.bawei.framework.UserManager;
import com.shopmall.bawei.net.NetFunction;
import com.shopmall.bawei.net.OkHttpHelper;
import com.shopmall.bawei.net.mode.AutoLogBean;
import com.shopmall.bawei.net.mode.BaseBean;
import com.shopmall.bawei.net.mode.LoginBean;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShopmallService extends Service {

    public class KsBinder extends Binder{
        public ShopmallService getService(){
            return ShopmallService.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        autoLogin(UserManager.getInstance().getToken());
        return START_NOT_STICKY;
    }

    private void autoLogin(String token) {
        Log.i("TAG", "autoLogin: "+token);
        OkHttpHelper.getApi().autoLogin(token).subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<LoginBean>,LoginBean>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        UserManager.getInstance().saveLoginBean(loginBean);
                        Log.i("auto", "onNext: 登录成功"+loginBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new KsBinder();
    }
}
