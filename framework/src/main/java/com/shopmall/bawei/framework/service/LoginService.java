package com.shopmall.bawei.framework.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.shopmall.bawei.net.Https;
import com.shopmall.bawei.net.HttpsFactory;
import com.shopmall.bean.Loginbean;
import com.shopmall.manager.ShopUserManager;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String token = ShopUserManager.getInstance().getToken();

        HashMap<String,String> map=new HashMap<>();
        map.put("token",token);
        Log.e("token",token);
        if (token==null){
             Toast.makeText(this, "自动登陆失败", Toast.LENGTH_SHORT).show();
        }else {
            HttpsFactory.getHttpsFactory().getinstance(Https.class)
                    .getlogin("autoLogin",map)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<Loginbean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Loginbean loginbean) {
                               if (loginbean.getCode().equals("200")){
                                   Toast.makeText(LoginService.this, ""+loginbean.getMessage(), Toast.LENGTH_SHORT).show();
                                   ShopUserManager.getInstance().setLogin(loginbean);
                                 //  ShopCarmanager.getShopCarmanager().ShopcarData();
                               }else {
                                   Toast.makeText(LoginService.this, ""+loginbean.getMessage(), Toast.LENGTH_SHORT).show();
                               }

                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(LoginService.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }

        return super.onStartCommand(intent, flags, startId);

    }
}
