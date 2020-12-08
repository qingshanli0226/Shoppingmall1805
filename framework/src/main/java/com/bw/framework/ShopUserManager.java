package com.bw.framework;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.bw.net.bean.LoginBean;
import com.bw.net.bean.ShopmallConstant;

import java.util.ArrayList;
import java.util.List;


public class ShopUserManager {

    private LoginBean loginBean;

    private static ShopUserManager instance;

    private Context context;


    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private List<IUserLoginChangedListener> listeners = new ArrayList<>();

    private ShopUserManager() {
    }

    public static ShopUserManager getInstance() {
        if (instance == null) {
            instance = new ShopUserManager();
        }

        return instance;
    }

    public void init(Context context) {
        sharedPreferences = context.getSharedPreferences(ShopmallConstant.spName, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        this.context = context;
    }

    //该函数，将当前应用程序的登录状态由未登录改成已登录
    public void saveLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;


        //使用sp存储token
        editor.putString("token",loginBean.getResult().getToken());
        editor.commit();
        Log.e("---", "saveLoginBean: "+loginBean.getResult().getToken() );
        //发送广播，通知当前应用用户已经登录成功
        Intent intent = new Intent();
        intent.setAction(ShopmallConstant.LOGIN_ACTION);
        context.sendBroadcast(intent);
    }

    public String getName(){
        if (loginBean != null){
            return loginBean.getResult().getName();
        }else {
            return null;
        }
    }


    //判断当前用户是否登录
    public boolean isUserLogin() {
        return loginBean != null;//如果loginBean不为空则代表已经登录
    }

    public String getToken() {
        if (loginBean != null) {
            Log.i("---", "getToken: "+loginBean.getResult().getToken());
            return loginBean.getResult().getToken();
        } else {
            return sharedPreferences.getString(ShopmallConstant.tokenName,"");
        }
    }

    public void registerUserLoginChangedListener(IUserLoginChangedListener listener){
        if (listeners.contains(listener)){
            listeners.add(listener);
        }
    }

    public void unRegisterUserLoginChangedListener(IUserLoginChangedListener listener){
        if (listeners.contains(listener)){
            listeners.remove(listener);
        }
    }

    public interface IUserLoginChangedListener {
        void onUserLogin(LoginBean loginBean);
        void onUserLoginOut();
    }
}
