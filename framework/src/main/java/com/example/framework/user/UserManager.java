package com.example.framework.user;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.framework.service.ShopMallService;
import com.example.net.bean.LoginBean;

public class UserManager {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static UserManager userManager;
    private static  LoginBean.ResultBean user;
    public void init(Context context){
        Intent intent = new Intent(context, ShopMallService.class);
        context.startService(intent);
        sharedPreferences=context.getSharedPreferences("auto",Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }
    public void spToken(){
        editor.putString("token",user.getToken());
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

}
