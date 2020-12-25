package com.shopmall.bawei.order.repostory;

import com.shopmall.bawei.order.model.OrderModel;

import framework.Contact;
import framework.IOrderData;

public
class OrderRepostory extends Contact.CenterOrderReposotry {

    @Override
    protected void createModel() {
        model = new OrderModel();
    }


    @Override
    public void goBindingPhone(String Url) {
        model.goBindingPhone(Url);
    }

    @Override
    public void goBindingPoint(String Url) {
        model.goBindingPoint(Url);
    }

    @Override
    public void goUnpaidOrder(IOrderData iOrderData) {
         model.goUnpaidOrder(iOrderData);
    }
}
