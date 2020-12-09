package com.shopmall.bawei.shopmall1805.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.net.Retrofitcreators;
import com.example.net.bean.AutoLoginBean;
import com.shopmall.bawei.framework.example.framework.user.UserManage;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MyService extends Service {

    @NonNull
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String token = UserManage.getInstance().quToken();
        Log.e("###","1111"+token);
        if (TextUtils.isEmpty(token)||token == null){
            Log.e("LQS","当前token为空,无法自动登录");
        }
        HashMap<String,String> map = new HashMap<>();
        map.put("token",token);
        Retrofitcreators.getiNetPresetenterWork().autoLogin(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AutoLoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AutoLoginBean autoLoginBean) {
                        Toast.makeText(MyService.this, "自动登录成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MyService.this, "自动登录失败" , Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });



        return super.onStartCommand(intent, flags, startId);


    }
}
