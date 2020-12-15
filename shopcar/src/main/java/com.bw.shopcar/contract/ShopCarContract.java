package com.bw.shopcar.contract;

import com.bw.framework.BasePresenter;
import com.bw.framework.IView;
import com.bw.net.bean.ShopCarBean;

import java.util.List;

public class ShopCarContract {

    public interface ShopCarView extends IView{
        void onProductNumChange(String result, int position, String newNum);
        void onProductSelected(String result, int position);
        void onAllSelected(String result);
        void onDeleteProducts(String result);

    }

    public abstract static class IShopCarPresenter extends BasePresenter<ShopCarView>{
        public abstract void updateProductNum(String productId, String productNum, String productName, String url, String productPrice, int position, String newNum);
        public abstract void updateProductSelected(String productId, boolean productSelected, String productName, String url, String productPrice, int position);
        public abstract void selectAllProduct(boolean isAllSelect);
        public abstract void deleteProducts(List<ShopCarBean> products);

    }

}
