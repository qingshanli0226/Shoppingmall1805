package com.bawei.framework;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.bawei.common.view.ErrorBean;
import com.bawei.common.view.ExceptionUtil;
import com.bawei.net.RetrofitCreate;
import com.bawei.net.mode.LoginBean;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new KsBinder();
    }

    public void autoLogin(String token) {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", token);
        RetrofitCreate.getApi().autoLogin(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        if (loginBean.getCode().equals("200")) {
                            ShopUserManager.getInstance().saveLoginBean(loginBean);
                        } else {

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ErrorBean errorBean = ExceptionUtil.getErrorBean(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
