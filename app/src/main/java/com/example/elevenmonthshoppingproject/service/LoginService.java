package com.example.elevenmonthshoppingproject.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.example.elevenmonthshoppingproject.MainActivity;
import com.example.net.RetorfitCreate;
import com.example.net.bean.AutoLoginBean;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginService  {

//
//
//    @Override
//    public int onStartCommand(final Intent intent, int flags, int startId) {
//
//
////        String token = ShopCarManager.getInstance().getToken();
//        String token = getSharedPreferences("gtlname", MODE_PRIVATE).getString("token", "");
//        Log.i("-----","123"+token);
//        HashMap<String,String> hashMap=new HashMap<>();
//        hashMap.put("token",token);
//        RetorfitCreate.getiNetworkserviceimpl().tokenbean(hashMap)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<AutoLoginBean>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(AutoLoginBean autoLoginBean) {
//                        if (autoLoginBean.getCode().equals("200")){
//                            Log.i("---","自动登录"+autoLoginBean.getResult().getToken());
//                            Toast.makeText(LoginService.this, "自动登录成功", Toast.LENGTH_SHORT).show();
//                            getSharedPreferences("gtlname",MODE_PRIVATE).edit().putString("token",autoLoginBean.getResult().getToken()).commit();
//
//                                    Intent intent1=new Intent(getApplicationContext(), MainActivity.class);
//                                    intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                    startActivity(intent1);
//                                    return;
//
//
//
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//        return super.onStartCommand(intent, flags, startId);
//    }
//
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
}
