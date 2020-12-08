package com.shopmall.framework;

import android.content.Context;
import android.content.SharedPreferences;

import com.shopmall.net.bean.LoginBean;

import java.util.LinkedList;
import java.util.List;

public class ShopUserManager {

    private LoginBean loginBean;

    private static ShopUserManager instance;

    private Context context;
    private List<IUserLoginChangeListener> listeners = new LinkedList<>();

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private ShopUserManager(){

    }

    public static ShopUserManager getInstance(){
        if (instance == null){
            instance = new ShopUserManager();
        }
        return instance;
    }

    public void init(Context context){
        sharedPreferences = context.getSharedPreferences(RestName.SHARE_NAME,RestName.SHARE_MODEL);
        editor = sharedPreferences.edit();
        this.context = context;
    }

    public void saveLoginBean(LoginBean loginBean){
        this.loginBean = loginBean;

        editor.putString(RestName.LOGIN_TOKEN,loginBean.getResult().getToken());
        editor.commit();

        for (IUserLoginChangeListener listener:listeners){
            listener.OnUserLogin(loginBean);
        }
    }

    public String getToken(){
        String string = sharedPreferences.getString(RestName.LOGIN_TOKEN, null);
        if (string!=null){
            return string;
        }else {
            return null;
        }
    }

    public String getUserName(){
        if (loginBean != null){
            return loginBean.getResult().getName();
        }else {
            return null;
        }
    }

    public boolean isUserLogin(){
        if (loginBean != null){
            return true;
        }else {
            return false;
        }
    }

    public void registerUserLoginChangeListener(IUserLoginChangeListener listener){
        if (!listeners.contains(listener)){
            listeners.add(listener);
        }
    }

    public void unRegisterUserLoginChangeListener(IUserLoginChangeListener listener){
        if (listeners.contains(listener)){
            listeners.remove(listener);
        }
    }

    public interface IUserLoginChangeListener{
        void OnUserLogin(LoginBean loginBean);
        void OnUserLogout();
    }
}
