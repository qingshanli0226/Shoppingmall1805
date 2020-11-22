package com.bawei.shopmall.shopcar.contract;

import com.shopmall.bawei.framework.BasePresenter;
import com.shopmall.bawei.framework.IView;
import com.shopmall.bawei.net.mode.InventoryBean;
import com.shopmall.bawei.net.mode.OrderInfoBean;
import com.shopmall.bawei.net.mode.ShopcarBean;

import java.util.List;

public class ShopcarContract {

    public interface IShopcarView extends IView {
        void onProductNumChange(String result, int position, String newNum);
        void onProductSelected(String result, int position);
        void onAllSelected(String result);
        void onDeleteProducts(String result);
        void onInventory(List<InventoryBean> inventoryBean);
        void onOrderInfo(OrderInfoBean orderInfoBean);
    }

    public static abstract class ShopcarPresenter extends BasePresenter<IShopcarView> {
        public abstract void updateProductNum(String productId, String productNum, String productName, String url, String productPrice, int position, String newNum);
        public abstract void updateProductSelected(String productId, boolean productSelected, String productName, String url, String productPrice, int position);
        public abstract void selectAllProduct(boolean isAllSelect);
        public abstract void deleteProducts(List<ShopcarBean> products);
        public abstract void checkInventory(List<ShopcarBean> products);
        public abstract void getOrderInfo(List<ShopcarBean> products);
    }
}
