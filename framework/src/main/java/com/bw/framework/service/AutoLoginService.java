package com.bw.framework.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.bw.framework.ShopUserManager;
import com.bw.net.RetraficCreator;
import com.bw.net.bean.LoginBean;
import com.bw.net.bean.ShopmallConstant;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AutoLoginService extends Service {

    public class KsBinder extends Binder {
        public AutoLoginService getService(){
            return AutoLoginService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new KsBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String token = ShopUserManager.getInstance().getToken();


        Log.i("---", "onStartCommand: "+token);
        if (TextUtils.isEmpty(token) || token == null){
            Toast.makeText(this, "token为空", Toast.LENGTH_SHORT).show();
        }
        HashMap<String,String> map = new HashMap<>();
        map.put("token",token);
        RetraficCreator.getiNetWorkApiService().autoLogin(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean autoLoginBean) {
                        ShopUserManager.getInstance().saveLoginBean(autoLoginBean);
                        Toast.makeText(AutoLoginService.this, "自动登录成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(AutoLoginService.this, "自动登录失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });


        return super.onStartCommand(intent, flags, startId);
    }
}
