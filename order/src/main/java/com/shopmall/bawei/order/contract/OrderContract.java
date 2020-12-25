package com.shopmall.bawei.order.contract;

import com.example.framework.BaseIPresenter;
import com.example.framework.IVIew;

public class OrderContract {

    public interface IOrderView extends IVIew {
        void onUpDataPhone(String phone);

        void onUpDataAddress(String address);
    }

    public static abstract class OrderPresenter extends BaseIPresenter<IOrderView>{
        public abstract void upDataPhone(String phone);
        public abstract void upDataAddress(String address);
    }

}
