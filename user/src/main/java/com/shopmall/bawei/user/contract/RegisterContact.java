package com.shopmall.bawei.user.contract;

import com.example.framework.BasePresenter;
import com.example.framework.IView;
import com.example.net.bean.LoginBean;
import com.example.net.bean.RegisterBean;

public class RegisterContact {
    public interface LoginView extends IView {
        void onregister(RegisterBean registerBean);
    }
    public abstract static class LoginPresenter extends BasePresenter<LoginView> {
        public abstract void getregister(String name,String password);
    }
}
