package com.example.framwork.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framwork.ShopUserManager;
import com.example.net.RetorfitCreate;
import com.example.net.ShopMallObserver;
import com.example.net.bean.AutoLoginBean;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String string = ShopUserManager.getInstance().getString(this);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token",string);
        RetorfitCreate.getiNetworkserviceimpl().tokenbean(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ShopMallObserver<AutoLoginBean>() {
                    @Override
                    public void onNext(AutoLoginBean autoLoginBean) {
                        if (autoLoginBean.getCode().equals("200")){
                            Log.i("---",""+autoLoginBean.getResult().getToken());
                            Toast.makeText(getApplicationContext(), "自动登录成功", Toast.LENGTH_SHORT).show();
                           getSharedPreferences("gtlname",MODE_PRIVATE).edit().putString("token",autoLoginBean.getResult().getToken()).commit();

                                ARouter.getInstance().build("/Main/MainActivity").navigation();

                            return;

                        }
                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {

                    }
                });


        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
