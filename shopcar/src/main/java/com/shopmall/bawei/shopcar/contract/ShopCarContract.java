package com.shopmall.bawei.shopcar.contract;

import com.shopmall.bawei.framework.BasePresenter;
import com.shopmall.bawei.framework.IView;
import com.shopmall.bawei.net.mode.ShopCarBean;

import java.util.List;

public class ShopCarContract {

    public interface IShopCarView extends IView{
        void onProductNumChanges(String result,int position,String newNum);
        void onProductSelected(String result,int position);
        void onAllSelected(String result);
        void onDeleteProducts(String result);
    }
    public static abstract class IShopCarPresenter extends BasePresenter<IShopCarView>{
        public abstract void updateProductNum(String productId,String productNum,String productName,String url,String productPrice,int position,String newNum);
        public abstract void updateProductSelected(String productId,boolean productSelected,String productName,String url,String productPrice,int position);
        public abstract void selectAllProduct(boolean isAllSelect);
        public abstract void deleteProducts(List<ShopCarBean> products);
    }
}
