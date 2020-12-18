package com.shopmall.bawei.pay.paymvp;

import com.example.net.bean.InventoryBean;
import com.example.net.bean.OrderInfoBean;
import com.example.net.bean.ShopcarBean;
import com.shopmall.bawei.framework.example.framework.BasePresenter;
import com.shopmall.bawei.framework.example.framework.IView;

import java.util.List;

public class PayContract {

    public interface orderView extends IView {
        void updatePhone(String numPhone);
        void updateAddress(String address);
        void onInventory(List<InventoryBean> inventoryBean);
        void onOrderInfo(OrderInfoBean orderInfoBean);
    }

    public abstract static class orderPresenter extends BasePresenter<orderView> {

        public abstract void updatePhoneData(String numPhone);
        public abstract void updateAddressData(String address);
        public abstract void checkInventory(List<ShopcarBean> products);
        public abstract void getOrderInfo(List<ShopcarBean> products);
    }

}
