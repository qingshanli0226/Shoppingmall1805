package com.shopmall.bawei.shopmall1805.ui.activity.contract;

import com.example.framework.BasePresenter;
import com.example.framework.IView;

public class BindContract {
    public interface IBindView extends IView {
        void onPhoneResult(String result);
        void onAdressResult(String result);
    }
    public abstract static class IBindPresenter extends BasePresenter<IBindView> {
        public abstract void BindPhone(String phone);
        public abstract void BindAdress(String adress);
    }
}
