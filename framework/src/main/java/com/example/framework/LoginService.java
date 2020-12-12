package com.example.framework;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.net.BaseBean;
import com.example.net.ConfigUrl;
import com.example.net.HttpRetrofitManager;
import com.example.net.INetPresetenterWork;
import com.example.net.LoginBean;
import com.example.net.ShopUserManger;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

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
        String token = ShopUserManger.getInstance().getToken();
        Log.e("###",""+token);
        if (token==null){
            return START_NOT_STICKY;
        }else {
            HashMap<String,String> map = new HashMap<>();
            map.put("token",token);
            HttpRetrofitManager.getHttpRetrofitManager()
                    .getRetrofit(ConfigUrl.BASE_URL)
                    .create(INetPresetenterWork.class)
                    .Autologin(map)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<BaseBean<LoginBean>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(BaseBean<LoginBean> loginBeanBaseBean) {
                            LoginBean loginBean = loginBeanBaseBean.getResult();
                            ShopUserManger.getInstance().ShopLoginManger(loginBean);
                            ShopUserManger.getInstance().setName(loginBeanBaseBean.getResult().getName());
                            EventBus.getDefault().postSticky("自动登录成功");
                            CacheManager.getInstance().getShopcarDataFromServer();
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });

        }
        return START_NOT_STICKY;
    }
}
