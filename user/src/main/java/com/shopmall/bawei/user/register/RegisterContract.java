package com.shopmall.bawei.user.register;

import com.example.framework.base.BasePresenter;
import com.example.framework.mvp.IView;
import com.example.net.bean.RegisterBean;

public interface RegisterContract {
    interface RegisterView extends IView{
        void onOk(RegisterBean bean);

    }
    public abstract class RegisterPresenter extends BasePresenter<RegisterView>{
        public abstract void register(String name,String password);
    }
}
