package framework.greendao;

import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;

import mode.LoginBean;
import view.ShopmallConstant;

public
class ShopUserManger {
    private LoginBean loginBean;

    private static  ShopUserManger shopUserManger;

    private Context context;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public static  ShopUserManger getInstance(){
        if (shopUserManger == null){
            synchronized (ShopUserManger.class){
                if (shopUserManger == null){
                    shopUserManger = new ShopUserManger();
                }
            }
        }
        return  shopUserManger;
    }

    public void init(Context context1){
        sharedPreferences = context.getSharedPreferences(ShopmallConstant.spName,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        this.context = context1;
    }

    public void saveLoginBean(LoginBean loginBean){
        this.loginBean = loginBean;

        //使用sp储存token

        editor.putString(ShopmallConstant.tokenName,loginBean.getToken());
        editor.commit();

        Intent intent = new Intent(ShopmallConstant.LOGIN_ACTION);
        context.sendBroadcast(intent);

    }

    public boolean isUserLogin(){
        return  loginBean!=null;
    }

    public String getToken(){
        if (loginBean!=null){
            return loginBean.getToken();
        }else {
            return "";
        }
    }


}
