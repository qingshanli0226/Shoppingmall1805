package com.shopmall.bawei.user.contract;

import com.example.net.bean.RegisterBean;
import com.shopmall.bawei.framework.example.framework.BasePresenter;
import com.shopmall.bawei.framework.example.framework.IView;

public class RegisterContact {
    public interface LoginView extends IView {
        void onregister(RegisterBean registerBean);
    }
    public abstract static class LoginPresenter extends BasePresenter<LoginView> {
        public abstract void getregister(String name,String password);
    }
}
