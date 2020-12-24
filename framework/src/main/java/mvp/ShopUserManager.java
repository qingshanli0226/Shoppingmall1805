package mvp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.common2.LoginBean;
import com.example.common2.UpdaptePhoneBean;
import com.example.common2.UpdataAddressBean;
import com.example.common2.UrlHelp;

import java.util.ArrayList;
import java.util.List;

public class ShopUserManager {

    private LoginBean loginBean;
    private UpdataAddressBean updataAddressBean;
    private UpdaptePhoneBean updaptePhoneBean;

    private static ShopUserManager instance;

    private Context context;
    private List<IUserLoginChangedListener> listeners=new ArrayList<>();

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

    public void saveaddrees(UpdataAddressBean updataAddressBean){
        this.updataAddressBean = updataAddressBean;

        if (updataAddressBean!=null) {
            //使用sp存储地址
            editor.putString(UrlHelp.userAddress,loginBean.getResult().getAddress()+"");
            editor.commit();

        }

    }
    public void savephone(UpdaptePhoneBean updaptePhoneBean){
        this.updaptePhoneBean = updaptePhoneBean;

        if (updaptePhoneBean!=null) {
            //使用sp存储手机号
            editor.putString(UrlHelp.userPhone,loginBean.getResult().getPhone()+"");
            editor.commit();

        }

    }


    //将当前应用程序的登录状态有未登录改成已登录

    public void saveLoginBean(LoginBean loginBean){
        this.loginBean = loginBean;
        Log.i("TAGtoken", "saveLoginBean: "+loginBean.getResult().getToken());

        if (loginBean!=null) {
            //使用sp存储token

            editor.putString(UrlHelp.tokenName, loginBean.getResult().getToken());
            editor.putString(UrlHelp.userName,loginBean.getResult().getName()+"");
            editor.commit();
            //通过接口回调通知其他模块的页面,当前用户已登录
            for (IUserLoginChangedListener listener : listeners) {
                listener.onUserLogin(loginBean);
            }

        }

    }
    //判断当前用户是否登录
    public boolean isUserLogin(){

        return loginBean!= null;//如果loginBean不为空则代表已经登录
    }
    public String getToken(){
        if (loginBean!=null){
            return loginBean.getResult().getToken();
        }else {
            return sharedPreferences.getString(UrlHelp.tokenName,"");
        }
    }
    public String getPhone(){
        if (loginBean!=null){
            return loginBean.getResult().getPhone()+"";
        }else {
            return sharedPreferences.getString(UrlHelp.userPhone,"");
        }
    }
    public String getAddrees(){
        if (loginBean!=null){
            return loginBean.getResult().getAddress()+"";
        }else {
            return sharedPreferences.getString(UrlHelp.userAddress ,"");
        }
    } public String getuserName(){
        if (loginBean!=null){
            return loginBean.getResult().getName()+"";
        }else {
            return sharedPreferences.getString(UrlHelp.userName ,"");
        }
    }
    public void registerUserLoginChangeListener(IUserLoginChangedListener listener){
        if (!listeners.contains(listener)){
            listeners.add(listener);
        }
    }

    public void unRegisterUserLoginChangeListener(IUserLoginChangedListener listener){
        if (!listeners.contains(listener)){
            listeners.remove(listener);
        }
    }

    public interface IUserLoginChangedListener{
        void  onUserLogin(LoginBean loginBean);
    }
}
