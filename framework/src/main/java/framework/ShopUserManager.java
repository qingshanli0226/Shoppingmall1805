package framework;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import mode.LoginBean;
import view.ShopmallConstant;

public class ShopUserManager {

    private LoginBean.ResultBean loginBean;
    private static ShopUserManager instance;

    private Context context;


    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private ShopUserManager() {

    }

    public static ShopUserManager getInstance() {
        if (instance == null) {
            synchronized (ShopUserManager.class){
                if (instance == null){
                    instance = new ShopUserManager();
                }
            }
        }
        return instance;
    }
    public void setPhone(String phone){
        if (phone==null){
            loginBean.setAddress(phone);
        }
    }
    public void setAddress(String address){
        if (address!=null){
           loginBean.setAddress(address);
        }
    }
    public LoginBean.ResultBean getLoginBean(){
        return loginBean;
    }
    public void init(Context context) {
        this.context = context;
        sharedPreferences = this.context.getSharedPreferences(ShopmallConstant.spName, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    //该函数，将当前应用程序的登录状态由未登录改成已登录
    public void saveLoginBean(LoginBean.ResultBean loginBean) {
        this.loginBean = loginBean;
        //使用sp存储token
        editor.putString(ShopmallConstant.tokenName, loginBean.getToken());
        editor.commit();
    }
    //判断当前用户是否登录
    public boolean isUserLogin() {
        return loginBean!=null;//如果loginBean不为空则代表已经登录
    }

}
