package com.shopmall.bawei.framework.example.framework.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.net.bean.LoginBean;

import java.util.LinkedList;
import java.util.List;

public class UserManage {

    private static UserManage userManager ;

    private LoginBean loginBean;

    private List<IUserLoginChangedListener> listeners = new LinkedList<>();
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor edit;
    private Context context;

    private UserManage(){

    }

    public static UserManage getInstance(){
        if (userManager == null){
            userManager = new UserManage();
        }
        return userManager;
    }



    public void nid(Context context){
        sharedPreferences = context.getSharedPreferences("login",Context.MODE_PRIVATE);
        edit = sharedPreferences.edit();
        edit.commit();
        this.context=context;
    }



    //存储token值
    public void saveLoginBean(LoginBean loginBean){
        this.loginBean = loginBean;
        edit.putString("token",loginBean.getToken());
        edit.commit();
        //通过接口回调通知其他模块的页面，当前用户已经登录
        for (IUserLoginChangedListener listener : listeners) {
            listener.onUserLogin(loginBean);
        }
    }

    //获取token值
    public String quToken(){
        if (loginBean != null){
            return loginBean.getToken();
        }else {
            return sharedPreferences.getString("token", "");
        }
    }
    //获取用户名
    public String quUsername(){
        if (loginBean != null){
            return loginBean.getName();
        }else {
            return null;
        }
    }

    //获取在线状态
    public boolean quFlag(){
        return loginBean != null;
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
        void onUserLogin(LoginBean loginBean);
        void onUserLogout();
    }
}
