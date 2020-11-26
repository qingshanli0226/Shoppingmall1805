package com.shopmall.bawei.user.register.contract;

import com.shopmall.bawei.framework.BasePresenter;
import com.shopmall.bawei.framework.IView;

public class RegisterContract {
    public interface IRegisterView extends IView{
        void onRegister(String registerBean);
    }
    public abstract static class IRegisterPresenter extends BasePresenter<IRegisterView>{
        public abstract void register(String name,String password);
    }
}
