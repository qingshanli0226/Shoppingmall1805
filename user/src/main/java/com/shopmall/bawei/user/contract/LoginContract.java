package com.shopmall.bawei.user.contract;


import com.shopmall.bawei.framework.BasePresenter;
import com.shopmall.bawei.framework.IView;
import com.shopmall.bawei.net.mode.LoginBean;

public class LoginContract {

    public interface ILoginView extends IView {
        void onLogin(LoginBean loginBean);
    }

    public static abstract class LoginPresenter extends BasePresenter<ILoginView> {
        public abstract void login(String userName, String password);
    }
}
