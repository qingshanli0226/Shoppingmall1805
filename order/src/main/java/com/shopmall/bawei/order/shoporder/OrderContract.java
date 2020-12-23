package com.shopmall.bawei.order.shoporder;

import com.example.framework.base.BasePresenter;
import com.example.framework.mvp.IView;
import com.example.net.bean.ConfirmServerPayResultBean;
import com.shopmall.bawei.pay.PayResult;

public interface OrderContract {
    interface IOrderView extends IView{
        void onConfirmServerPayResultOk(ConfirmServerPayResultBean bean);
    }
    public abstract class OrderPresenter extends BasePresenter<IOrderView> {
        public abstract void confirmServerPayResult(String outTradeNo, PayResult result,Boolean clientPayResult);
    }
}
