package com.example.framework;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.framework.ShopUsermange;
import com.example.net.Retrofitcreators;
import com.example.net.bean.BaseBean;
import com.example.net.bean.LoginBean;


import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MyService extends Service{
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String gettoken = ShopUsermange.getInstance().gettoken();
        Log.e("###",gettoken);
        if (gettoken == null){
            return START_STICKY;
        }else {
            HashMap<String,String> map = new HashMap<>();
            map.put("token",gettoken);
            Retrofitcreators.getiNetPresetenterWork().autologin(map)
                    .delay(3, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<BaseBean<LoginBean>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(BaseBean<LoginBean> loginBeanBaseBean) {
                            LoginBean loginBean = loginBeanBaseBean.getResult();
//                            ShopUsermange.getInstance().setName(loginBean.getName());
//                            ShopUsermange.getInstance().setPassword(loginBean.getPassword());
                            ShopUsermange.getInstance().ShopLoginmange(loginBean);
                            CacheManager.getInstance().getshopCardateserver();
                            EventBus.getDefault().postSticky("自动登录成功");
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
