package com.bawei.framework;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.bawei.net.RetrofitCreate;
import com.bawei.net.mode.BaseBean;
import com.bawei.net.mode.LoginBean;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShopmallService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new KsBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        autoLogin(ShopUserManager.getInstance().getToken());
        return super.onStartCommand(intent, flags, startId);
    }

    public class KsBinder extends Binder {
        public ShopmallService getService() {
            return ShopmallService.this;
        }
    }

    public void autoLogin(String tokenValue) {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", tokenValue);
        RetrofitCreate.getApi().autoLogin(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean<LoginBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean<LoginBean> loginBeanBaseBean) {
                        LoginBean result = loginBeanBaseBean.getResult();
                        ShopUserManager.getInstance().saveLoginBean(result);
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
