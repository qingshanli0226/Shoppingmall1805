package com.shopmall.bawei.order;

import com.example.framework.base.BasePresenter;
import com.example.framework.mvp.IView;

public interface OrderContract {
    interface IOrderView extends IView{

    }
    public abstract class OrderPresenter extends BasePresenter<IOrderView> {

    }
}
