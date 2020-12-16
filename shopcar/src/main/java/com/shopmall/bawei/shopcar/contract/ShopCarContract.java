package com.shopmall.bawei.shopcar.contract;

import com.shopmall.bawei.framework.BasePresenter;
import com.shopmall.bawei.framework.IView;
import com.shopmall.bawei.net.mode.InventoryBean;
import com.shopmall.bawei.net.mode.OrderInfoBean;
import com.shopmall.bawei.net.mode.ShopCarBean;

import java.util.List;
import java.util.ListIterator;

public class ShopCarContract {

    public interface IShopCarView extends IView{
        void onProductNumChanges(String result,int position,String newNum);
        void onProductSelected(String result,int position);
        void onAllSelected(String result);
        void onDeleteProducts(String result);
        void onInventory(List<InventoryBean> list);
        void onOrderInfo(OrderInfoBean orderInfoBean);
    }
    public static abstract class IShopCarPresenter extends BasePresenter<IShopCarView>{
        public abstract void updateProductNum(String productId,String productNum,String productName,String url,String productPrice,int position,String newNum);
        public abstract void updateProductSelected(String productId,boolean productSelected,String productName,String url,String productPrice,int position);
        public abstract void selectAllProduct(boolean isAllSelect);
        public abstract void deleteProducts(List<ShopCarBean> products);
        public abstract void checkInventory(List<ShopCarBean> products);
        public abstract void getOrderInfo(List<ShopCarBean> products);
    }
}
