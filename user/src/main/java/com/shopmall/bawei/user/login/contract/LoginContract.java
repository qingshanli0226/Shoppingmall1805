package com.shopmall.bawei.user.login.contract;

import com.shopmall.bawei.framework.BasePresenter;
import com.shopmall.bawei.framework.IView;
import com.shopmall.bawei.net.mode.LoginBean;

public class LoginContract {
    public interface ILoginView extends IView{
        void onLogin(LoginBean loginBean);
    }
    public abstract static class ILoginPresenter extends BasePresenter<ILoginView>{
        public abstract void login(String name,String password);
    }

}
