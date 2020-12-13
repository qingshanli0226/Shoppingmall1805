package mvp.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.common2.LoginBean;
import com.example.common2.UrlHelp;

public class ShopUserManager {

    private LoginBean loginBean;

    private static ShopUserManager instance;

    private Context context;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private ShopUserManager(){

    }

    public static ShopUserManager getInstance(){
        if (instance==null){
            instance=new ShopUserManager();
        }
        return instance;
    }

    public void init (Context context){
        sharedPreferences = context.getSharedPreferences(UrlHelp.spName,Context.MODE_PRIVATE);
        editor =sharedPreferences.edit();
        this.context = context;
    }
    //将当前应用程序的登录状态有未登录改成已登录

    public void saveLoginBean(LoginBean loginBean){
        this.loginBean = loginBean;

        //使用sp存储token
        editor.putString(UrlHelp.tokenName,loginBean.getResult().getToken());
        editor.commit();




    }
    //判断当亲用户是否登录
    public boolean isUserLogin(){

        return loginBean!= null;//如果loginBean不为空则代表已经登录
    }
    public String getToken(){
        if (loginBean!=null){
            Log.i("lhj", "getToken: "+loginBean.getResult().getToken());
            return loginBean.getResult().getToken();
        }else {
            return sharedPreferences.getString(UrlHelp.tokenName,"");
        }
    }
}
