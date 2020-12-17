package com.example.elevenmonthshoppingproject.address.contract;

import com.example.framwork.BasePresenter;
import com.example.framwork.IView;
import com.example.net.bean.MessageBean;

public class AddressContract {

    public interface AddressIView extends IView{
        void onAddress(String messageBean);
    }

    public static abstract class AddressPresenter extends BasePresenter<AddressIView>{
        public abstract void getAddress(String AddressName);
    }
}
