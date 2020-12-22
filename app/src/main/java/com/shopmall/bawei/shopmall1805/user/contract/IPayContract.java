package com.shopmall.bawei.shopmall1805.user.contract;

import com.shopmall.bawei.framework.BasePresenter;
import com.shopmall.bawei.framework.IView;
import com.shopmall.bawei.net.mode.PayBean;

import java.util.List;

public class IPayContract {
    public interface IPayView extends IView{
        void onPay(List<PayBean> payBeanList);
    }

    public abstract static class IPayPresenter extends BasePresenter<IPayView>{
        public abstract void getPayBeans();
    }
}
