package com.bw.user.contract;


import com.bw.common.BasePresenter;
import com.bw.common.IView;

public class RegisterContract {

    public interface IRegisterView extends IView {
        void onRegister(String result);
    }

    public static abstract class ReigsterPresenter extends BasePresenter<IRegisterView> {
        public abstract void register(String name, String password);
    }
}
