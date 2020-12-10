package com.shopmall.bawei.framework.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.shopmall.bean.Loginbean;
import com.shopmall.restname.RestName;

import java.util.ArrayList;
import java.util.List;

public class ShopUserManager  {
    private volatile static ShopUserManager shareManager=null;
    private Context context;
    private SharedPreferences sharedPreferences;
    private Loginbean loginbean;
    private SharedPreferences.Editor editor;
    private List<IUserListener> iUserListeners=new ArrayList<>();
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


    public void setLogin(Loginbean loginbean){
        this.loginbean=loginbean;
        //添加toekn值
       editor.putString(RestName.LOGIN_TOKEN,loginbean.getResult().getToken()).commit();

        /**
         * 登录成功后通知每个页面该用户上线
         */
        for (IUserListener iUserListener : iUserListeners) {
              iUserListener.OnUserLogin(loginbean);
        }
    }

    /**
     * 获取token值
     * @return
     */
    public String getToken(){
        String string = sharedPreferences.getString(RestName.LOGIN_TOKEN, null);
        if (string!=null){
            return string;
        }else {
            return null;
        }

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

    /**
     * 该接口不存在添加
     * @param iUserListener
     */
    public void RegistUserLogin(IUserListener iUserListener){
          if (!iUserListeners.contains(iUserListener)){
              iUserListeners.add(iUserListener);
          }
    }

    /**
     * 该接口存在删除
     * @param iUserListener
     */
    public void UnRegistUserLogin(IUserListener iUserListener){
          if (iUserListeners.contains(iUserListener)){
              iUserListeners.remove(iUserListener);
          }
    }

    public interface IUserListener{
          void OnUserLogin(Loginbean loginbean);
          void OnUserLoginout();
    }



}
