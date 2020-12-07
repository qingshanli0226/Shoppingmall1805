package com.shopmall.bawei.framework.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

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
        return super.onStartCommand(intent,flags,startId);
    }

    private void autoLogin(String token) {
        HashMap<String,String> params = new HashMap<>();
        params.put("token",token);
        OkHttpHelper.getApi().autoLogin(params).subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<LoginBean>,LoginBean>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        UserManager.getInstance().saveLoginBean(loginBean);
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
