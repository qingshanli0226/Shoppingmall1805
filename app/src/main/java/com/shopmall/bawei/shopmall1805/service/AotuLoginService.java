package com.shopmall.bawei.shopmall1805.service;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.bw.framework.ShopUserManager;
import com.bw.net.bean.AutoLoginBean;
import com.bw.net.RetraficCreator;
import com.bw.net.bean.ShopmallConstant;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AotuLoginService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String token = getSharedPreferences(ShopmallConstant.spName, MODE_PRIVATE).getString("token", "");

        Log.i("---", "onStartCommand: "+token);
        if (TextUtils.isEmpty(token) || token == null){
            Toast.makeText(this, "token为空", Toast.LENGTH_SHORT).show();
        }
        HashMap<String,String> map = new HashMap<>();
        map.put("token",token);
        RetraficCreator.getiNetWorkApiService().autoLogin(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AutoLoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AutoLoginBean autoLoginBean) {
                        Toast.makeText(AotuLoginService.this, "自动登录成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(AotuLoginService.this, "自动登录失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });


        return super.onStartCommand(intent, flags, startId);
    }
}
