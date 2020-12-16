package com.shopmall.bawei.order;

import com.example.framework.base.BasePresenter;
import com.example.framework.mvp.IView;
import com.example.net.bean.CheckInventoryBean;

import java.util.List;

public interface OrderContract {
    interface IOrderView extends IView{
        void onCheckOk(CheckInventoryBean bean);
    }
    public abstract class OrderPresenter extends BasePresenter<IOrderView> {
        public abstract void checkInventory(List<OrderBean> list);
    }
}
