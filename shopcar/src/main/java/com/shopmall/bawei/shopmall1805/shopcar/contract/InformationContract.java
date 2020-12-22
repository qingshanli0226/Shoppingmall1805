package com.shopmall.bawei.shopmall1805.shopcar.contract;

import com.shopmall.bawei.shopmall1805.framework.BasePresenter;
import com.shopmall.bawei.shopmall1805.framework.IView;
import com.shopmall.bawei.shopmall1805.net.entity.OrderInfoBean;
import com.shopmall.bawei.shopmall1805.net.entity.ShopcarBean;

import java.util.HashMap;
import java.util.List;

public class InformationContract {
    public interface InformationView extends IView {
        void onUpDataPhone(String result);
        void onAddressUpData(String result);

    }
    public static abstract class InformationPresenter extends BasePresenter<InformationView> {
        public abstract void UpDataPhone(String phone);
        public abstract void UpDataAddress(String address);
    }
}
