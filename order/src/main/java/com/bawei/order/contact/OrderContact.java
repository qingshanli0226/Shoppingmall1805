package com.bawei.order.contact;

import com.bawei.framework.BasePresenter;
import com.bawei.framework.IView;
import com.bawei.net.mode.OrderInfoBean;
import com.bawei.net.mode.ShopcarBean;

import java.util.List;

public class OrderContact {
    public interface IOrderView extends IView {
        void onOrderInfo(OrderInfoBean orderInfoBean);

        void onConfirmServerPayResult(String result);
    }

    public static abstract class OrderPresenter extends BasePresenter<IOrderView> {
        public abstract void getOrderInfo(List<ShopcarBean> list);

        public abstract void PostConfirmServerPayResult(OrderInfoBean orderInfoBean,boolean clientPayResult);
    }
}
