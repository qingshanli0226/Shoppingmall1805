package com.example.framework;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.net.Confing;
import com.example.net.bean.LoginBean;

import java.util.LinkedList;
import java.util.List;

public class ShopUsermange {
    String name;
    String password;

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private LoginBean loginBean;
    private Context context;
    private static ShopUsermange instance;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private List<IUserLoginChangeLiestener> liesteners = new LinkedList<>();
    private ShopUsermange(){

    }
    public static ShopUsermange getInstance(){
        if (instance == null) {
            instance = new ShopUsermange();
        }
        return instance;
    }
    public void init(Context context){
        sharedPreferences = context.getSharedPreferences(Confing.spName,Context.MODE_PRIVATE);
        editor =sharedPreferences.edit();
        this.context = context;
    }
    //判断用户是否登录
    public boolean isUserLogin(){
        return loginBean!=null;
    }
    //该函数，将当前的登录程序的登录状态从未登录变成已登录
    public void ShopLoginmange(LoginBean loginBean) {
        this.loginBean = loginBean;

        //使用sp储存token
        editor.putString(Confing.token,loginBean.getToken());
        editor.commit();

        //通过接口回调通知其他的界面，当前用户已经登录
        for (IUserLoginChangeLiestener listenter:liesteners) {
            listenter.onUserLogin(loginBean);
        }
    }
    //将token和loginbean清空
    public void clearshopbean(){
        editor.clear();
        loginBean = null;
        for (IUserLoginChangeLiestener liestener : liesteners) {
            liestener.onUserlogout();
        }
    }
    //将电话存入缓存
    public void setPhone(String phone){
        if (phone!=null){
            loginBean.setPhone(phone);
        }
    }
    //将地址存入缓存
    public void setAddress(String address){
        if (address!=null){
            loginBean.setAddress(address);
        }
    }
    //让别的类拿到电话
    public Object getPhone(){
        Log.e("EE",""+loginBean.getPhone());
        return loginBean.getPhone();
    }
    //让别的类拿到地址
    public Object getAddress(){
        return loginBean.getAddress();
    }
    public String gettoken(){
        if (loginBean!=null){
            return loginBean.getToken();
        }else {
            return sharedPreferences.getString(Confing.token,"");
        }

    }
    public void registerUserLoginChangeListenter(IUserLoginChangeLiestener liestener){
        if (!liesteners.contains(liestener)){
            liesteners.add(liestener);
        }
    }
    public void unRegisterUserLoginChangeListenter(IUserLoginChangeLiestener liestener){
        if (liesteners.contains(liestener)){
            liesteners.remove(liestener);
        }
    }
    public interface IUserLoginChangeLiestener{
        void onUserLogin(LoginBean loginBean);
        void onUserlogout();
    }
}
