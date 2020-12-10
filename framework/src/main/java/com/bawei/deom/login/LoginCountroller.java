package com.bawei.deom.login;

import com.bawei.deom.BaseAroute;
import com.bawei.deom.IView;

import java.util.List;

import bean.LoginBean;
import bean.RegisterBean;

public class LoginCountroller {
    public interface LoginView extends IView {
           void onlogin(LoginBean loginBean);
           void onregister(RegisterBean registerBean);
    }
    public abstract static class LoginShow extends BaseAroute<LoginView> {
      public abstract   void registerShow(String name,String password);
      public abstract   void loginShow(String name,String password);
    }
}
