package com.shopmall.bawei.order.contract;

import com.example.framework.BaseIPresenter;
import com.example.framework.IVIew;
import com.example.net.IntonVoryBean;
import com.example.net.OrderInfoBean;
import com.example.net.ShopcarBean;

import java.util.List;

public class TakeContract {

    public interface ITakeView extends IVIew {
        void onCheckIntonvory(List<IntonVoryBean> intonVoryBeans);        //检查多个购物车服务端的商品数量
        void getOrderInfo(OrderInfoBean orderInfoBean);        //检查多个购物车服务端的商品数量
        void getConfirmServerPayResult(String result); //检查是是否支付成功
    }

    public static abstract class TakePresenter extends BaseIPresenter<TakeContract.ITakeView> {
        public abstract void checkIntonvory(List<ShopcarBean> shopcarBeanList);
        public abstract void orderinfo(List<ShopcarBean> shopcarBeanList);
        public abstract void ConfirmServerPayResult(OrderInfoBean orderInfoBean,boolean flag);
    }

}
