package com.example.user;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.net.Contants;
import com.example.net.bean.LoginBean;

public class ShopCarManager {
        private LoginBean loginBean;

        private static ShopCarManager shopCarManager;

        private Context context;

        private SharedPreferences sharedPreferences;

        private SharedPreferences.Editor editor;

        private ShopCarManager(){

        }

        public static ShopCarManager getInstance(){
            if (shopCarManager==null){
                shopCarManager=new ShopCarManager();
            }
            return shopCarManager;
        }

        public void init(Context context){

                context.getSharedPreferences(Contants.SHOPCAR_NAME,Context.MODE_PRIVATE);
                editor=sharedPreferences.edit();
                this.context=context;
        }

        public void saveLoginbean(LoginBean loginBean){
                    this.loginBean=loginBean;
            //使用sp卡存储
            editor.putString(Contants.SHOPCAR_TOKEN,loginBean.getResult().getToken()).commit();

            Intent intent = new Intent();
            intent.setAction(Contants.LOGIN_ACTION);
            context.sendBroadcast(intent);
        }

        //判断当前用户是否登陆
       public boolean isUserLogin(){
            return loginBean!=null;
       }

      public String getToken(){
            if (loginBean!=null){
               return loginBean.getResult().getToken();
            }else {
                return "";
            }
      }

}
