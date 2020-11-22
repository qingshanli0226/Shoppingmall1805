package com.shopmall.bawei.framework;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.shopmall.bawei.common.ShopmallConstant;
import com.shopmall.bawei.net.mode.LoginBean;

public class ShopUserManager {

    private LoginBean loginBean;

    private static ShopUserManager instance;

    private Context context;


    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

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
        editor.putString(ShopmallConstant.tokenName, loginBean.getToken());
        editor.commit();

        //发送广播，通知当前应用用户已经登录成功
        Intent intent = new Intent();
        intent.setAction(ShopmallConstant.LOGIN_ACTION);
        context.sendBroadcast(intent);
    }


    //判断当前用户是否登录
    public boolean isUserLogin() {
        return loginBean != null;//如果loginBean不为空则代表已经登录
    }

    public String getToken() {
        if (loginBean!=null) {
            return loginBean.getToken();
        } else {
            return "";
        }
    }









}
