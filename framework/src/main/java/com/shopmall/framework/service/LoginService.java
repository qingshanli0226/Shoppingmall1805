package com.shopmall.framework.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.shopmall.net.Https;
import com.shopmall.net.RetrofitFactory;
import com.shopmall.net.bean.LoginBean;
import com.shopmall.net.manager.ShopUserManager;

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

        HashMap<String, String> map = new HashMap<>();
        map.put("token",token);
        if (token==null){
            Toast.makeText(this, "自动登录失败", Toast.LENGTH_SHORT).show();
        }else {
            RetrofitFactory.getInstance().create(Https.class)
                    .getLogin("autoLogin",map)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<LoginBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(LoginBean loginBean) {
                            if (loginBean.getCode().equals("200")){
                                ShopUserManager.getInstance().saveLoginBean(loginBean);
                            }else {
                                Toast.makeText(LoginService.this, ""+loginBean.getMessage(), Toast.LENGTH_SHORT).show();
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
