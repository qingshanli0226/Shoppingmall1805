package com.shopmall.bawei.shopmall1805.ui.activity.checkmvp;


import com.shopmall.bawei.framework.example.framework.BasePresenter;
import com.shopmall.bawei.framework.example.framework.IView;

public class ProductDetailContract {

    public interface IProductDetailView extends IView {
        void onCheckOneProduct(String productNum);
        void onAddProduct(String addResult);
        void onProductNumChange(String result);
    }

    public static abstract class ProductDetailPresenter extends BasePresenter<IProductDetailView> {
        public abstract void checkOneProductNum(String productId, String productNum);
        public abstract void addOneProduct(String productId, String productNum, String productName, String url, String productPrice);
        public abstract void updateProductNum(String productId, String productNum, String productName, String url, String productPrice);
    }
}
