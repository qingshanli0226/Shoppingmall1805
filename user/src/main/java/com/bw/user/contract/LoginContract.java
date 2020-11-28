package com.bw.user.contract;

import com.bw.common.BasePresenter;
import com.bw.common.IView;
import com.bw.net.bean.LoginBean;

public class LoginContract {

    public interface ILoginView extends IView {
        void onLogin(LoginBean loginBean);
    }

    public static abstract class LoginPresenter extends BasePresenter<ILoginView> {
        public abstract void login(String userName, String password);
    }
}
