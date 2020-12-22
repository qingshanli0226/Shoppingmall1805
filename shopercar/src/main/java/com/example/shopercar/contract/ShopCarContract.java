package com.example.shopercar.contract;

import com.example.framwork.BasePresenter;
import com.example.framwork.IView;
import com.example.net.InventoryBean;
import com.example.net.OrderInfoBean;
import com.example.net.bean.ConfirmServerPayResultBean;
import com.example.net.bean.ShopcarBean;

import java.util.List;

public class ShopCarContract {
    public interface ShopCarIView extends IView{
        void onProductNumChange(String result,int position,String newNum);
        void onProductSelect(String result,int position);
        void onAllSelect(String result);
        void onDeleteProduct(String result);
        void onInventory(List<InventoryBean> inventoryBeans);
        void onOnderInfo(OrderInfoBean orderInfoBean);
        void onConfirmServerPay(ConfirmServerPayResultBean confirmServerPayResultBean);
    }

    public static abstract class ShopCarPresenter extends BasePresenter<ShopCarIView>{
        public abstract void updateProductNum(String productId, String productNum, String productName, String url, String productPrice, int position, String newNum);
        public abstract void updateProductSelected(String productId, boolean productSelected, String productName, String url, String productPrice, int position);
        public abstract void selectAllProduct(boolean isAllSelect);
        public abstract void deleteProducts(List<ShopcarBean> products);
        public abstract void checkInventory(List<ShopcarBean> products);
        public abstract void getOrderInfo(List<ShopcarBean> products);
        public abstract void getConfirmServerPay();
    }
}
