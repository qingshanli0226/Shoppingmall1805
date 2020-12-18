package com.shopmall.bawei.framework.example.framework.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.net.NetFunction;
import com.example.net.Retrofitcreators;
import com.example.net.bean.BaseBean;
import com.example.net.bean.LoginBean;
import com.shopmall.bawei.framework.example.framework.manager.UserManage;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MyService extends Service{

    public class KsBinder extends Binder{
        public MyService getService(){
            return MyService.this;
        }
    }


        @Override
        public IBinder onBind(Intent intent) {
            return new KsBinder();
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            String token = UserManage.getInstance().quToken();
            Log.e("---","从单例获取的token值"+token);
            if (TextUtils.isEmpty(token)||token == null){
                Log.e("---","当前token为空,无法自动登录");
            }
            HashMap<String,String> map = new HashMap<>();

            map.put("token",token);
            Retrofitcreators.getiNetPresetenterWork().autoLogin(map)
                    .subscribeOn(Schedulers.io())
                    .map(new NetFunction<BaseBean<LoginBean>,LoginBean>())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<LoginBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(LoginBean loginBean) {
                            UserManage.getInstance().saveLoginBean(loginBean);
                            Log.e("---","更新的token值"+loginBean.getToken());
                            Toast.makeText(MyService.this, "自动登陆成功", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(MyService.this, "自动登陆失败", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onComplete() {

                        }
                    });



            return super.onStartCommand(intent, flags, startId);


        }
    }


