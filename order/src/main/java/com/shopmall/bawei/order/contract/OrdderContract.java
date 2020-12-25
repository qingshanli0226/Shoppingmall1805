package com.shopmall.bawei.order.contract;

import com.example.common2.GetShopCarBean;

import java.util.List;

import http.InventoryBean;
import http.OrderInfoBean;
import mvp.view.BasePresenter;
import mvp.view.IView;

public class OrdderContract {

    public interface ISetShopCar extends IView{


        void onInventory(List<InventoryBean> inventoryBean);
        void onOrderInfo(OrderInfoBean orderInfoBean);
    }
    public static abstract class OrderPresenter extends BasePresenter<ISetShopCar>{

        public abstract void checkInventory(List<GetShopCarBean> products);
        public abstract void getOrderInfo(List<GetShopCarBean> products);
    }

}
