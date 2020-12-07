package com.example.framework.service;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.framework.user.UserManager;
import com.example.net.RetrofitCreater;
import com.example.net.bean.AutoLoginBean;
import com.example.net.bean.LoginBean;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShopMallService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new SMBinder();
    }
    public class SMBinder extends Binder{
        public ShopMallService getService(){
            return ShopMallService.this;
        }
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        SharedPreferences auto = getSharedPreferences("auto", MODE_PRIVATE);
        String token = auto.getString("token", "");
        Log.i("Yoyo111", "onStartCommand: "+token);
        HashMap<String, String> map = new HashMap<>();
        map.put("token",token);
        autoLogin(map);
        return super.onStartCommand(intent, flags, startId);
    }

    private void autoLogin(final HashMap<String, String> map) {
        RetrofitCreater.getiNetWorkApi().autoLogin(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AutoLoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AutoLoginBean autoLoginBean) {
                        Toast.makeText(ShopMallService.this, ""+autoLoginBean.getMessage(), Toast.LENGTH_SHORT).show();
                        if(autoLoginBean.getCode().equals("200")){
                            AutoLoginBean.ResultBean result = autoLoginBean.getResult();
                            LoginBean.ResultBean user = new LoginBean.ResultBean();
                            user.setAddress(result.getAddress());
                            user.setEmail(result.getEmail());
                            user.setId(result.getId());
                            user.setMoney(result.getMoney());
                            user.setName(result.getName());
                            user.setToken(result.getToken());
                            user.setPhone(result.getPhone());
                            user.setPoint(result.getPoint());
                            user.setAvatar(result.getAvatar());
                            UserManager.getInstance().bindUser(user);
                            UserManager.getInstance().spToken();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(ShopMallService.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
