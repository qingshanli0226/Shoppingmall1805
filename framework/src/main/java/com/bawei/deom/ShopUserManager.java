package com.bawei.deom;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.LinkedList;
import java.util.List;

import bean.LoginBean;

public class ShopUserManager {

    public LoginBean loginBean;

    public static ShopUserManager instance;

    public Context context;
    public static List<IUserLoginChangedListener> listeners = new LinkedList<>();


    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;

    public ShopUserManager() {

    }

    public static ShopUserManager getInstance() {
        if (instance == null) {
            instance = new ShopUserManager();

        }

        return instance;
    }

    public void init(Context context) {
        sharedPreferences = context.getSharedPreferences("shopmall", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        this.context = context;
    }

    //该函数，将当前应用程序的登录状态由未登录改成已登录
    public void saveLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;

        //使用sp存储token
        editor.putString("token", loginBean.getResult().getToken());
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
            return loginBean.getResult().getName();
        } else {
            return null;
        }
    }

    public String getToken() {
        if (loginBean!=null) {
            return loginBean.getResult().getToken();
        } else {
            return sharedPreferences.getString("token", "");//如果用户没有登录的话，从sp中拿token
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
