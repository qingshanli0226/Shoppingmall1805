package com.shopmall.bawei.shopcar.contract;

import com.shopmall.bawei.framework.BasePresenter;
import com.shopmall.bawei.framework.IView;

public class BindContract {
    public interface IBindView extends IView{
        void onBindPhone(String phone);
        void onBindAddress(String address);
    }

    public abstract static class IBindPresenter extends BasePresenter<IBindView>{
        public abstract void bindPhone(String phone);
        public abstract void bindAddress(String address);
    }
}
