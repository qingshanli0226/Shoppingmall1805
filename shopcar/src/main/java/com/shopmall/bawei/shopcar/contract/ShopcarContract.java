package com.shopmall.bawei.shopcar.contract;

import com.example.framework.BaseIPresenter;
import com.example.framework.IVIew;
import com.example.net.ShopcarBean;

import java.util.List;

public class ShopcarContract {

    public interface IShopcarView extends IVIew{
        void onProductNumChange(String result,int position,String newNum);
        void onProductSelected(String result,int position);
        void onAllSelected(String result);
        void onDeleteProducts(String result);
    }

    public static abstract class ShopcarPresenter extends BaseIPresenter<IShopcarView>{
        public abstract void updateProductNum(String productId, String productNum, String productName, String url, String productPrice, int position, String newNum);
        public abstract void updateProductSelected(String productId, boolean productSelected, String productName, String url, String productPrice, int position);
        public abstract void selectAllProduct(boolean isAllSelect);
        public abstract void deleteProducts(List<ShopcarBean> products);
        public abstract void checkInventory(List<ShopcarBean> products);
        public abstract void getOrderInfo(List<ShopcarBean> products);
    }

}
