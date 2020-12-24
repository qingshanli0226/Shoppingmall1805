package com.shopmall.bawei.shopmall1805.myfragment.contract;

import com.example.common2.UpdaptePhoneBean;
import com.example.common2.UpdataAddressBean;

import mvp.view.BasePresenter;
import mvp.view.IView;

public class AddressContract {
    public interface IAddress extends IView{

        void onAddess(UpdataAddressBean updataAddressBean);

        void onPhone(UpdaptePhoneBean updaptePhoneBean);
    }

    public static abstract class AddressPresenter extends BasePresenter<IAddress>{
        public abstract void addAddress(String address);
        public abstract void addPhone(String phone);
    }
}
