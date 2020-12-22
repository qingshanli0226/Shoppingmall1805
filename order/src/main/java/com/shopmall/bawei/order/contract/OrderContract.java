package com.shopmall.bawei.order.contract;

import com.shopmall.bawei.framework.BasePresenter;
import com.shopmall.bawei.framework.IView;
import com.shopmall.bawei.net.mode.OrderInfoBean;
import com.shopmall.bawei.net.mode.ShopCarBean;

import java.util.List;

public class OrderContract {
    public interface IOrderView extends IView {
        void onOrderInfo(OrderInfoBean orderInfoBean);
        void onConfirmServerPayResult(boolean payResult);
    }

    public abstract static class IOrderPresenter extends BasePresenter<IOrderView>{
        public abstract void getOrderInfo(List<ShopCarBean> products);
        public abstract void confirmServerPayResult(OrderInfoBean orderInfoBean,boolean isPaySuccess);
    }
}
