package com.shopmall.bawei.shopmall1805.service;


import android.app.Service;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import android.content.Intent;
import net.FoodService;
import net.RxjavaRetortUlis;
import java.util.HashMap;

import framework.CacheManagerc;
import framework.ShopUserManager;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mode.BaseBean;
import mode.LoginBean;
import view.ShopmallConstant;

public
class AutomactionLoginService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String token = getSharedPreferences(ShopmallConstant.spName, MODE_PRIVATE).getString(ShopmallConstant.tokenName, null);
        if (TextUtils.isEmpty(token)){
            Toast.makeText(this, "当前token为空，无法自动登录", Toast.LENGTH_SHORT).show();
        }
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("token",token);
        Log.i("====","tokenService"+token);



         RxjavaRetortUlis.getInstance()
                .create(FoodService.class)
                .autoLogin(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(LoginBean loginBeanBaseBean) {
                        Log.i("====","ccc"+loginBeanBaseBean.getCode());
                        if (loginBeanBaseBean.getCode().equals("200")){
                            ShopUserManager.getInstance().saveLoginBean(loginBeanBaseBean);
                            CacheManagerc.getInstance().getShopcarDataFromServer();
                            Toast.makeText(AutomactionLoginService.this, "登录成功", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(AutomactionLoginService.this, "登录失败"+loginBeanBaseBean.getCode(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("LQS","onError");
                    }

                    @Override
                    public void onComplete() {

                    }
                });



        return super.onStartCommand(intent, flags, startId);
    }
}
