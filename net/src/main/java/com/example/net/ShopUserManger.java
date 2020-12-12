package com.example.net;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

public class ShopUserManger {
    private LoginBean loginBean;
    private Context context;
    private static ShopUserManger instance;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private List<IUserLoginChangeListener> listeners = new LinkedList<>();
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private ShopUserManger(){


    }
    public static ShopUserManger getInstance(){
        if (instance == null) {
            instance = new ShopUserManger();
        }
        return instance;
    }
    public void init(Context context){
        sharedPreferences = context.getSharedPreferences("boluo",Context.MODE_PRIVATE);
        editor =sharedPreferences.edit();
        this.context = context;
    }
    //判断用户是否登录
    public boolean isUserLogin(){
        return loginBean!=null;
    }
    //该函数，将当前的登录程序的登录状态从未登录变成已登录
    public void ShopLoginManger(LoginBean loginBean) {
        this.loginBean = loginBean;

        //使用sp储存token
        editor.putString("token",loginBean.getToken());
        Log.i("login", "ShopLoginmange: "+loginBean.getToken());
        editor.commit();

        //通过接口回调通知其他的界面，当前用户已经登录
        for (IUserLoginChangeListener listenter:listeners) {
            listenter.onUserLogin(loginBean);
        }


    }
    public String getToken(){

        return sharedPreferences.getString("token","");
    }

    public void requesterUserLoginChangeListener(IUserLoginChangeListener listener){
        if (listeners.contains(listener)){
            listeners.add(listener);
        }
    }

    public void unRegisterUserLoginChangeListener(IUserLoginChangeListener listener){
        if (listeners.contains(listener)){
            listeners.remove(listener);
        }
    }


    public interface IUserLoginChangeListener{
        void onUserLogin(LoginBean loginBean);
        void onUserlogout();
    }
}
