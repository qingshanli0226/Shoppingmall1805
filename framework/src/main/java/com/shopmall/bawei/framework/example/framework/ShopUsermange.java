package com.example.framework;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.net.Confing;
import com.example.net.bean.LoginBean;

import java.util.LinkedList;
import java.util.List;

public class ShopUsermange {
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
    //该函数，将当前的登录程序的登录状态从未登录变成已登录
    public void ShopLoginmange(LoginBean loginBean) {
        this.loginBean = loginBean;

        //使用sp储存token
        editor.putString(Confing.spName,loginBean.getToken());
        editor.commit();

        //通过接口回调通知其他的界面，当前用户已经登录
        for (IUserLoginChangeLiestener listenter:liesteners) {
            listenter.onUserLogin(loginBean);
        }
    }
    public interface IUserLoginChangeLiestener{
        void onUserLogin(LoginBean loginBean);
        void onUserlogout();
    }
}
