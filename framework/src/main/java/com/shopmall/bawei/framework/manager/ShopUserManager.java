package com.shopmall.bawei.framework.manager;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.shopmall.bawei.framework.restname.RestName;
import com.shopmall.bean.Loginbean;

public class ShopUserManager extends Application {
    private volatile static ShopUserManager shareManager=null;
    private Context context;
    private SharedPreferences sharedPreferences;
    private Loginbean loginbean;
    private SharedPreferences.Editor editor;
    /**
     * 单例- 双重锁
     * @return
     */
    public static ShopUserManager getInstance(){
          if (shareManager==null){
              synchronized (ShopUserManager.class){
                  if (null==shareManager){
                      shareManager=new ShopUserManager();
                  }
              }
          }
          return shareManager;
    }


    private ShopUserManager(){

    }

    public void init(Context context){
       sharedPreferences=context.getSharedPreferences(RestName.SHARE_NAME, RestName.SHARE_MODEL);
        editor=sharedPreferences.edit();
        this.context=context;

    }


    public void setLogin(Loginbean login){
        this.loginbean=login;
        //添加toekn值
       editor.putString(RestName.LOGIN_TOKEN,login.getResult().getToken()).commit();
    }

    /**
     * 获取用户的账号
     * @return
     */
    public String getUserName(){
          if (loginbean!=null){
              return loginbean.getResult().getName();
          }else {
              return null;
          }
    }

    /**
     * 为true则为用户登陆成功
     * @return
     */
    public boolean isLogin(){
         if (loginbean!=null){
             return true;
         }else {
             return false;
         }
    }



}
