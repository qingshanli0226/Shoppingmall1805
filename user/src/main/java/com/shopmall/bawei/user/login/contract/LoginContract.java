package com.shopmall.bawei.user.login.contract;

import com.shopmall.bawei.framework.BasePresenter;
import com.shopmall.bawei.framework.IView;

public class LoginContract {
    public interface ILoginView extends IView{

    }
    public abstract static class ILoginPresenter extends BasePresenter<ILoginView>{

    }

}
