package com.shopmall.bawei.shopmall1805.service.autologin;

import com.example.net.bean.AutoLoginBean;
import com.shopmall.bawei.framework.example.framework.BasePresenter;
import com.shopmall.bawei.framework.example.framework.IView;

public class AutoLoginContract {

    public interface AutoLoginView extends IView{
        void MyautologinView(AutoLoginBean autoLoginBean);
    }

    public abstract static class AutoLoginPrensnter extends BasePresenter<AutoLoginView>{
        public abstract void MyautologinPrensnter(String token);
    }

}
