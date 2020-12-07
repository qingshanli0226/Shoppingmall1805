package com.shopmall.bawei.user.contract;

import com.example.framework.BaseIPresenter;
import com.example.framework.IVIew;
import com.example.net.LoginBean;

public interface LoginContract {
    interface LoginView extends IVIew{
        void getLoginCode(LoginBean loginBean);
    }

    abstract class LoginPresenter extends BaseIPresenter<LoginContract.LoginView>{
        public abstract void getUser(String name,String password);
    }
}
