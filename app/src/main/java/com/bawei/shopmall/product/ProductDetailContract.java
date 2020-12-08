package com.bawei.shopmall.product;

import com.bawei.framework.BasePresenter;
import com.bawei.framework.IView;

public class ProductDetailContract {
    public interface IProductDetailView extends IView {
        void onCheckOneProduct(String productNum);

        void onAddProduct(String addResult);

        void onProductNumChange(String resylt);
    }

    public static abstract class ProductDetailPresenter extends BasePresenter<IProductDetailView> {
        public abstract void checkOneProductNum(String productId, String productNum);

        public abstract void addOneProduct(String productId, String productNum, String productName, String url, String productPrice);

        public abstract void updateProdyctNum(String productId, String productNum, String productName, String url, String productPrice);
    }
}
