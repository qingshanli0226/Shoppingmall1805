package com.shopmall.bawei.shopmall1805.shopcar.contract;

import com.example.common2.CheckBean;
import com.example.common2.GetShopCarBean;

import mvp.view.BasePresenter;
import mvp.view.IView;

public class ShopCarContract {

    public interface IGetShopCar extends IView{
        void onGetShopCar(GetShopCarBean getShopCarBean);
    }
    public static abstract class GetShopCarPresenter extends BasePresenter<IGetShopCar>{
        public abstract void getshopcar();
    }

}
