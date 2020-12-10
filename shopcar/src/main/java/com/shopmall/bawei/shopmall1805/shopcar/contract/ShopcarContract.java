package com.shopmall.bawei.shopmall1805.shopcar.contract;

import com.shopmall.bawei.shopmall1805.framework.BasePresenter;
import com.shopmall.bawei.shopmall1805.framework.IView;
import com.shopmall.bawei.shopmall1805.net.entity.OrderInfoBean;
import com.shopmall.bawei.shopmall1805.net.entity.ShopcarBean;

import java.util.List;

public class ShopcarContract {

    public interface IShopcarView extends IView {
        void onOrderInfo(List<ShopcarBean> beanList);
    }
    public static abstract class ShopcarPresenter extends BasePresenter<IShopcarView> {
        public abstract void getOrderInfo();
    }
}
