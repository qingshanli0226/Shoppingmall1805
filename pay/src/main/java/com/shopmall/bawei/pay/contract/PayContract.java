package com.shopmall.bawei.pay.contract;

import com.example.framework.BasePresenter;
import com.example.framework.IView;
import com.example.net.bean.ConfirmBean;
import com.example.net.bean.IntonVoryBean;
import com.example.net.bean.OrderInfoBean;
import com.example.net.bean.ShopcarBean;

import java.util.List;

public class PayContract {
    public interface IOrderView extends IView {
        void onCheckIntonvory(List<IntonVoryBean> intonVoryBeans);        //检查多个购物车服务端的商品数量
        void getOrderInfo(OrderInfoBean orderInfoBean);        //检查多个购物车服务端的商品数量
        void getConfirmServerPayResult(String result); //检查是是否支付成功
    }
    public abstract static class IOrderPresenter extends BasePresenter<IOrderView> {
        public abstract void checkIntonvory(List<ShopcarBean> shopcarBeanList);
        public abstract void orderinfo(List<ShopcarBean> shopcarBeanList);
        public abstract void ConfirmServerPayResult(OrderInfoBean orderInfoBean,boolean flag);

    }
}
