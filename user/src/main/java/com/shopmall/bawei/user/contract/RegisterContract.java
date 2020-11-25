package com.shopmall.bawei.user.contract;


import com.shopmall.bawei.framework.BasePresenter;
import com.shopmall.bawei.framework.IView;

public class RegisterContract {

    public interface IRegisterView extends IView {
        void onRegister(String result);
    }

    public static abstract class ReigsterPresenter extends BasePresenter<IRegisterView> {
        public abstract void register(String name, String password);
    }
}
