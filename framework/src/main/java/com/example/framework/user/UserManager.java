package com.example.framework.user;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.framework.service.ShopMallService;
import com.example.net.bean.LoginBean;

import java.util.LinkedList;
import java.util.List;

public class UserManager {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static UserManager userManager;
    private List<IUserLoginChangedListener> listeners = new LinkedList<>();
    private static  LoginBean.ResultBean user;
    public void init(Context context){
        sharedPreferences=context.getSharedPreferences("auto",Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();

    }
    public void spToken(){
        editor.putString("token",user.getToken());
        editor.apply();
    }
    public static UserManager getInstance(){
       if(userManager==null){
           userManager=new UserManager();
       }
        return userManager;
    }
    public void bindUser(LoginBean.ResultBean user){
       if(this.user==null){
           this.user=user;
       }
    }

    public void outLogin(){
        user=null;
    }
    public static boolean isLogin(){
        return user!=null;
    }
    public interface IUserLoginChangedListener{
        void OnUserLogin(LoginBean loginBean);
        void onUserLogout();

    }
    public void registerUserLoginChangeListener(IUserLoginChangedListener listener){
        if(!listeners.contains(listener)){
            listeners.add(listener);
        }
    }
    public void UnRegisterUserLoginChangeListener(IUserLoginChangedListener listener){
        if(!listeners.contains(listener)){
            listeners.remove(listener);
        }
    }
}
