package com.example.user.contract;

import com.example.framwork.BasePresenter;
import com.example.framwork.IView;
import com.example.net.bean.RegisterBean;

public class RegisterContract {

    public interface IRegisterView extends IView {
        void onregister(RegisterBean registerBean);
    }
    public static abstract class RegisterPresenter extends BasePresenter<IRegisterView>{
        public abstract void register(String name,String password);
    }
}
