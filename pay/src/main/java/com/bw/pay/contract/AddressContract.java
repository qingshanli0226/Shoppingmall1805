package com.bw.pay.contract;

import com.bw.framework.BasePresenter;
import com.bw.framework.IView;
import com.bw.net.InventoryBean;
import com.bw.net.OrderInfoBean;
import com.bw.net.bean.ShopCarBean;

import java.util.List;

public class AddressContract  {
    public interface AddressView extends IView{
        void onUpdateNumberOk(String result);
        void onUpdateAddressOk(String result);
        void onInventory(List<InventoryBean> inventoryBean);
        void onOrderInfo(OrderInfoBean orderInfoBean);

    }

    public abstract static class IAddressPresenter extends BasePresenter<AddressView>{
        public abstract void updateNumber(String number);
        public abstract void updateAddress(String address);
        public abstract void checkInventory(List<ShopCarBean> products);
        public abstract void getOrderInfo(List<ShopCarBean> products);
    }
}
