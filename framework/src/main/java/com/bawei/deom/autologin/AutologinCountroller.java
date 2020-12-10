package com.bawei.deom.autologin;

import com.bawei.deom.BaseAroute;
import com.bawei.deom.IView;

import bean.LoginBean;

public class AutologinCountroller {
    public interface AutoLoginView extends IView {
               void onMyautologinView(LoginBean autoLoginBeen);
    }
    public abstract static class AutoLoginShow extends BaseAroute<AutoLoginView> {
              public abstract   void MyautologinShow(String token);

    }
}
