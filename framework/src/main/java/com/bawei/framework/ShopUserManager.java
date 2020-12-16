package com.bawei.framework;

import android.content.Context;
import android.content.SharedPreferences;

import com.bawei.common.view.NetConfig;
import com.bawei.net.mode.LoginBean;

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
        sharedPreferences = context.getSharedPreferences(NetConfig.spName, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        this.context = context;
    }

    public void saveLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;

        editor.putString(NetConfig.tokenName, loginBean.getResult().getToken());
        editor.commit();

        for (IUserLoginChangedListener listener : listeners) {
            listener.onUserLogin(loginBean);
        }
    }

    public void banDingPhone(String phone) {
        LoginBean.ResultBean result = this.loginBean.getResult();
        result.setPhone(phone);
    }

    public void banDingAddress(String address) {
        LoginBean.ResultBean result = this.loginBean.getResult();
        result.setAddress(address);
    }

    public void logoutUser() {
        if (loginBean != null) {
            this.loginBean = null;
            editor.clear();
            editor.commit();
            for (IUserLoginChangedListener listener : listeners) {
                listener.onUserLogout();
            }
        }
    }


    public boolean isUserLogin() {
        return loginBean != null;//如果loginBean不为空则代表已经登录
    }

    public String getName() {
        if (loginBean != null) {
            return loginBean.getResult().getName();
        } else {
            return null;
        }
    }

    public String getPhone() {
        if (loginBean != null) {
            return String.valueOf(loginBean.getResult().getPhone());
        } else {
            return null;
        }
    }

    public String getAddress() {
        if (loginBean != null) {
            return String.valueOf(loginBean.getResult().getAddress());
        } else {
            return null;
        }
    }

    public String getToken() {
        if (loginBean != null) {
            return loginBean.getResult().getToken();
        } else {
            return sharedPreferences.getString(NetConfig.tokenName, "");
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

    public boolean isBanDingPhone() {
        if (loginBean.getResult().getPhone() != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isBanDingAddress() {
        if (loginBean.getResult().getAddress() != null) {
            return true;
        } else {
            return false;
        }
    }

    public interface IUserLoginChangedListener {
        void onUserLogin(LoginBean loginBean);

        void onUserLogout();
    }
}
