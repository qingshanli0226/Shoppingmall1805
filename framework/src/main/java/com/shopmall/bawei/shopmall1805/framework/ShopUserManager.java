package com.shopmall.bawei.shopmall1805.framework;


import android.content.Context;
import android.content.SharedPreferences;

import com.shopmall.bawei.shopmall1805.common.ShopmallConstant;
import com.shopmall.bawei.shopmall1805.net.entity.LoginBean;

import java.util.LinkedList;
import java.util.List;

public class ShopUserManager {
    private LoginBean.ResultBean loginBean;
    private static ShopUserManager instance=null;
    private Context contexts;
    private List<IUserLoginChangedListener> listeners=new LinkedList<>();

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    public ShopUserManager() {

    }
    public static ShopUserManager getInstance() {
        if(instance==null){
            instance = new ShopUserManager();
        }
        return instance;
    }
    public void init(Context context){
        this.contexts = context;
        sharedPreferences = contexts.getSharedPreferences(ShopmallConstant.spName,contexts.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    public void saveLoginBean(LoginBean.ResultBean loginBean){
        this.loginBean = loginBean;
        editor.putString(ShopmallConstant.tokenName,loginBean.getToken());
        editor.commit();

        for (IUserLoginChangedListener listener : listeners) {
            listener.onUserLogin(loginBean);
        }
    }
    public boolean isUserLogin(){
        return loginBean!=null;
    }
    public String getName(){
        if(loginBean!=null){
            return loginBean.getName();
        }else {
            return null;
        }
    }
    public String getToken(){
        if(loginBean!=null){
            return loginBean.getToken();
        }else {
            return sharedPreferences.getString(ShopmallConstant.tokenName,"");
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
    public interface IUserLoginChangedListener{
        void onUserLogin(LoginBean.ResultBean loginBean);
        void oUserLogout();
    }
}
