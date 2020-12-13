package com.shopmall.bawei.shopmall1805.goods.contract;

import mvp.view.BasePresenter;
import mvp.view.IView;

public class AddShopCarContract {
    public interface IAddShopCar extends IView {
        void onAddShopCar(String addResult);
    }

    public static abstract class AddShopCarPresenter extends BasePresenter<IAddShopCar>{
        public abstract void addShopCar(String productId, String productNum, String productName, String url, String productPrice);
    }
}

