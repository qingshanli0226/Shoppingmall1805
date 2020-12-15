package com.shopmall.bawei.user.telandadress;

import com.example.framework.base.BasePresenter;
import com.example.framework.mvp.IView;
import com.example.net.bean.UpDateAddressBean;
import com.example.net.bean.UpdatePhoneBean;

public interface BindTelAndAddressContract {
    interface IBindTelAndAddressView extends IView{
        void onBindTelOk(UpdatePhoneBean bean);
        void onBindAddressOk(UpDateAddressBean bean);
    }
    public abstract class BindTelAndAddressPresenter extends BasePresenter<IBindTelAndAddressView>{
        public abstract void bindTel(String tel);
        public abstract void bindAddress(String adress);
    }
}
