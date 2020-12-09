package com.shopmall.bawei.shopmall1805.framework.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.shopmall.bawei.shopmall1805.framework.ShopUserManager;
import com.shopmall.bawei.shopmall1805.net.RetrofitUtils;
import com.shopmall.bawei.shopmall1805.net.entity.LoginBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import java.util.HashMap;

public class ShopmallService extends Service {

    public class KsBinder extends Binder {
        public ShopmallService getService() {
            return ShopmallService.this;
        }
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        autoLogin(ShopUserManager.getInstance().getToken());
        return START_NOT_STICKY;
    }
    @Override
    public IBinder onBind(Intent intent) {
        return new KsBinder();
    }
    public void autoLogin(String tokenValue) {
        HashMap<String,String> params = new HashMap<>();
        params.put("token", tokenValue);
        Log.i("TAG", "autoLogin: "+tokenValue);
        RetrofitUtils.getiNetPresetenterWork().autoLogin(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(LoginBean loginBean) {
                        ShopUserManager.getInstance().saveLoginBean(loginBean.getResult());
                        Toast.makeText(ShopmallService.this, "自动登陆", Toast.LENGTH_SHORT).show();
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
