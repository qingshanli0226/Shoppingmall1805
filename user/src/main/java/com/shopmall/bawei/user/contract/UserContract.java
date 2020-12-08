package com.shopmall.bawei.user.contract;

import android.os.IInterface;

import com.example.common2.LoginBean;
import com.example.common2.RegisterBean;

import java.util.HashMap;

import mvp.view.BasePresenter;
import mvp.view.IView;
import retrofit2.http.PUT;

public class UserContract {
    public interface IUser extends IView{
        void onLogin(LoginBean loginBean);
        void onRegister(RegisterBean registerBean);
    }
    public static abstract class UserPresenter extends BasePresenter<IUser>{
        public abstract void getILoginBean(String name,String password);
        public abstract void getIRegisterBean(String name,String password);
    }
}
