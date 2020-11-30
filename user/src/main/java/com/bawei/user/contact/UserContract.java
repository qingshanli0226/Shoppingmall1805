package com.bawei.user.contact;

import com.bawei.framework.BasePresenter;
import com.bawei.framework.IView;
import com.bawei.net.mode.LoginBean;
import com.bawei.net.mode.RegisterBean;

public class UserContract {
    public interface IUserView extends IView {
        void login(LoginBean loginBean);

        void register(RegisterBean registerBean);
    }

    public static abstract class IUserPresenter extends BasePresenter<IUserView> {
        public abstract void loginUser(String username,String password);

        public abstract void registerUser(String username,String password);
    }
}
