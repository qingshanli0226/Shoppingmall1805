package com.shopmall.bawei.user.contract;

import com.example.framework.BasePresenter;
import com.example.framework.IView;
import com.example.net.bean.LoginBean;

public class LoginContact {
    public interface LoginView extends IView {
        void onlogin(LoginBean loginBean);
    }
    public abstract static class LoginPresenter extends BasePresenter<LoginView> {
        public abstract void getlogin(String name,String password);
    }
}
