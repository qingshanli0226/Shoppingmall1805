package com.shopmall.bawei.shopmall1805.shopcar.contract;

import com.example.common2.CheckBean;
import com.example.common2.GetShopCarBean;

import java.util.List;

import http.InventoryBean;
import http.OrderInfoBean;
import mvp.view.BasePresenter;
import mvp.view.IView;

public class ShopCarContract {

    public interface ISetShopCar extends IView{
        void onProductNumChange(String result, int position, String newNum);
        void onProductSelected(String result, int position);
        void onAllSelected(String result);
        void onDeleteProducts(String result);
        void onInventory(List<InventoryBean> inventoryBean);
        void onOrderInfo(OrderInfoBean orderInfoBean);
    }
    public static abstract class GetShopCarPresenter extends BasePresenter<ISetShopCar>{
        public abstract void updateProductNum(String productId, String productNum, String productName, String url, String productPrice, int position, String newNum);
        public abstract void updateProductSelected(String productId, boolean productSelected, String productName, String url, String productPrice, int position);
        public abstract void selectAllProduct(boolean isAllSelect);
        public abstract void deleteProducts(List<GetShopCarBean> products);
        public abstract void checkInventory(List<GetShopCarBean> products);
        public abstract void getOrderInfo(List<GetShopCarBean> products);
    }

}
