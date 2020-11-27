package com.shopmall.bawei.user.contract;

import com.example.framework.BaseIPresenter;
import com.example.framework.IVIew;

public interface LoginContract {
    interface LoginView extends IVIew{
        void getLoginCode(String message);
    }

    abstract class LoginPresenter extends BaseIPresenter<LoginContract.LoginView>{
        public abstract void getUser(String name,String password);
    }
}
