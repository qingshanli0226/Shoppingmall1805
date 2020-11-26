package com.shopmall.bawei.user.login;

import com.example.framework.base.BasePresenter;
import com.example.framework.mvp.IView;
import com.example.net.bean.LoginBean;

public interface LoginContract {
    interface LoginView extends IView{
        void onOk(LoginBean bean);
        void onError(String msg);
    }
    public abstract class LoginPresenter extends BasePresenter<LoginView>{
        public abstract void login(String name,String pwd);
    }
}
