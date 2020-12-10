package com.bawei.deom;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;

import bean.LoginBean;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MyServer extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String token = getSharedPreferences("login", MODE_PRIVATE).getString("login", "123");

       if (TextUtils.isEmpty(token)){
           Log.e("LQS","当前token为空,无法自动登录");
       }
        HashMap<String,String> map=new HashMap<>();
        map.put("token",token);
        ClassInterface.getUserInterface().autoLogin(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean autoLoginBeen) {
                                 if (autoLoginBeen.getCode().equals("200")){
                                     ShopUserManager.getInstance().saveLoginBean(autoLoginBeen);
                                     Toast.makeText(MyServer.this, "自动登录成功", Toast.LENGTH_SHORT).show();
                                     Log.e("自动登录","自动登录成功");
//                                     getSharedPreferences("login", Context.MODE_PRIVATE).edit().putString("login",autoLoginBeen.getResult().getToken()).commit();
                                 }
                                ;



                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return super.onStartCommand(intent, flags, startId);
    }
}
