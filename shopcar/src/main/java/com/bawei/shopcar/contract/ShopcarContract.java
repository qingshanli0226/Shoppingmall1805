package com.bawei.shopcar.contract;

import com.bawei.framework.BasePresenter;
import com.bawei.framework.IView;
import com.bawei.net.mode.InventoryBean;
import com.bawei.net.mode.OrderInfoBean;
import com.bawei.net.mode.ShopcarBean;

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
