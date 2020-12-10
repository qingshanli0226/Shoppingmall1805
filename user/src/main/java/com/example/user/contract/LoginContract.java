package com.example.user.contract;

import com.example.framwork.BasePresenter;
import com.example.framwork.IView;
import com.example.net.bean.LoginBean;

public class LoginContract{

    public interface ILoginView extends IView{
        void onLogin(LoginBean loginBean);
    }
    public static abstract class LoginPresenter extends BasePresenter<ILoginView>{
        public abstract void login(String name,String password);
    }
}
