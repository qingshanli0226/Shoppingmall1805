package com.example.framwork;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.net.bean.AutoLoginBean;
import com.example.net.bean.LoginBean;

import java.util.LinkedList;
import java.util.List;

public class ShopUserManager {

    private LoginBean loginBean;

    private AutoLoginBean autoLoginBean;

    private static ShopUserManager instance;

    private Context context;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private List<IUserLoginChangedListener> listeners = new LinkedList<>();

    private ShopUserManager(){
    }

    public static ShopUserManager getInstance(){
        if (instance==null){
            instance=new ShopUserManager();
        }
        return instance;
    }

    public void init(Context context){
        sharedPreferences=context.getSharedPreferences("gtlname",Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        this.context=context;
    }

//    public void saveAutoLogin(AutoLoginBean autoLoginBean){
//        this.autoLoginBean=autoLoginBean;
//        editor.putString("token",autoLoginBean.getToken());
//        editor.commit();
//
//    }

    public String getString(Context context){
        String string = context.getSharedPreferences("gtlname", Context.MODE_PRIVATE).getString("token", "");

        return string;
    }

    public void saveLoginbean(LoginBean loginBean){
        this.loginBean=loginBean;
        //使用sp存储

        editor.putString("token",loginBean.getToken());
        editor.commit();


    }
    //判断登陆状态
    public boolean isUserLogin(){
        return loginBean!=null;
    }

    public boolean isAutoUserLogin(){
        return autoLoginBean!=null;
    }

    public String getToken(){
        if (loginBean!=null){
            return loginBean.getToken();
        }else {
            return "";
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
