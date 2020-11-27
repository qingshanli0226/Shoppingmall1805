package com.shopmall.bawei.framework;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.shopmall.bawei.common.ShopmallConstant;
import com.shopmall.bawei.net.mode.LoginBean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ShopUserManager {

    private LoginBean loginBean;

    private static ShopUserManager instance;

    private Context context;
    private List<IUserLoginChangedListener> listeners = new LinkedList<>();


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

        //通过接口回调通知其他模块的页面，当前用户已经登录
        for(IUserLoginChangedListener listener:listeners) {
            listener.onUserLogin(loginBean);
        }
    }


    //判断当前用户是否登录
    public boolean isUserLogin() {
        return loginBean != null;//如果loginBean不为空则代表已经登录
    }

    public String getName() {
        if (loginBean!=null) {
            return loginBean.getName();
        } else {
            return null;
        }
    }

    public String getToken() {
        if (loginBean!=null) {
            return loginBean.getToken();
        } else {
            return sharedPreferences.getString(ShopmallConstant.tokenName, "");//如果用户没有登录的话，从sp中拿token
        }
    }

    public void registerUserLoginChangeListener(IUserLoginChangedListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public void unRegisterUserLoginChangeListener(IUserLoginChangedListener listener) {
        if (listeners.contains(listener)) {
            listeners.remove(listener);
        }
    }

    public interface IUserLoginChangedListener {
        void onUserLogin(LoginBean loginBean);
        void onUserLogout();
    }











}
