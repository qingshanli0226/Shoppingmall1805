package com.shopmall.bawei.framework;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.shopmall.bawei.common.UrlHelper;
import com.shopmall.bawei.net.MyNetApi;
import com.shopmall.bawei.net.OkHttpHelper;
import com.shopmall.bawei.net.TokenInterceptor;
import com.shopmall.bawei.net.mode.LoginBean;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserManager{

    private LoginBean loginBean;

    private static UserManager instance;

    private Context context;
    private List<IUserLoginChangedListener> listeners = new LinkedList<>();


    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private UserManager() {

    }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();

        }

        return instance;
    }

    public boolean isBindPhone(){
        if(loginBean.getPhone() != null){
            return true;
        }
        return false;
    }

    public boolean isBindAddress(){
        if(loginBean.getAddress() != null){
            return true;
        }
        return false;
    }

    public void init(Context context) {
        sharedPreferences = context.getSharedPreferences(UrlHelper.spName, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        this.context = context;
    }

    public void saveLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;

        editor.putString(UrlHelper.tokenName, loginBean.getToken());
        editor.commit();

        for(IUserLoginChangedListener listener:listeners) {
            listener.onUserLogin(loginBean);
        }
    }

    public void checkOutLoginBean(){
        loginBean = null;
        editor.clear().commit();
        for(IUserLoginChangedListener listener:listeners){
            listener.onUserLogout();
        }
    }


    public boolean isUserLogin() {
        return loginBean != null;
    }

    public String getName() {
        if (loginBean!=null) {
            return loginBean.getName();
        } else {
            return null;
        }
    }

    public String getAddress(){
        if(loginBean != null){
            return loginBean.getAddress();
        } else {
            return null;
        }
    }

    public String getPhone(){
        if(loginBean != null){
            return loginBean.getPhone();
        } else {
            return null;
        }
    }

    public String getToken() {
        if (loginBean!=null) {
            return loginBean.getToken();
        } else {
            return sharedPreferences.getString(UrlHelper.tokenName,"");
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

