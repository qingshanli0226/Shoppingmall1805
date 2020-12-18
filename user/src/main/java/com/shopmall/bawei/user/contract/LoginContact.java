package com.shopmall.bawei.user.contract;

import com.example.net.bean.LoginBean;
import com.shopmall.bawei.framework.example.framework.BasePresenter;
import com.shopmall.bawei.framework.example.framework.IView;

public class LoginContact {
    public interface LoginView extends IView {
        void onlogin(LoginBean loginBean);
    }
    public abstract static class LoginPresenter extends BasePresenter<LoginView> {
        public abstract void getlogin(String name,String password);
    }
}
