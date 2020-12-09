package com.shopmall.bawei.shopmall1805.user.contract;

import com.shopmall.bawei.shopmall1805.net.entity.LoginBean;
import com.shopmall.bawei.shopmall1805.framework.BasePresenter;
import com.shopmall.bawei.shopmall1805.framework.IView;

import java.util.HashMap;

public class LoginContract {
    public interface LoginView extends IView{
        void onLoginDate(LoginBean loginBean);
    }
    public static abstract class LoginPresenter extends BasePresenter<LoginView>{
        public abstract void getLoginData(HashMap<String,String> map);
    }
}
