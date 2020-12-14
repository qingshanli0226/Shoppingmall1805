package com.example.framework.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.net.bean.LoginBean;

import java.util.LinkedList;
import java.util.List;

public class UserManager {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static UserManager userManager;
    private List<IUserLoginChangedListener> listeners = new LinkedList<>();
    private static  LoginBean.ResultBean user;

    private UserManager() {
    }

    public void init(Context context){
        sharedPreferences=context.getSharedPreferences("auto",Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();

    }
    public void saveToken(){
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
           saveToken();
           for (IUserLoginChangedListener listener : listeners) {
               listener.onUserLogin(user);
           }
       }

    }

    public void outLogin(){
        user=null;
    }
    public  boolean isLogin(){
        return user!=null;
    }
    public  boolean isBindAdress(){
        if(user.getAddress()!=null&&!user.getAddress().equals("")){
            return true;
        }
        return false;
    }
    public  boolean isBindTel(){
        if(user.getPhone()!=null&&!user.getPhone().equals("")){
            return true;
        }
        return false;
    }
    public void updataAdress(String adress){
        if(user!=null){
            user.setAddress(adress);
        }
    }
    public void updataTel(Integer phone){
        if(user!=null){
            user.setPhone(phone);
        }
    }
    public interface IUserLoginChangedListener{
        void onUserLogin(LoginBean.ResultBean user);
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
