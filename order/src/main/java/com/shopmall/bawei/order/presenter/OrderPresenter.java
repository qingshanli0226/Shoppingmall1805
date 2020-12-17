package com.shopmall.bawei.order.presenter;

import com.shopmall.bawei.order.repostory.OrderRepostory;

import framework.Contact;

public
class OrderPresenter extends Contact.CenterOrderPresenter {
    public OrderPresenter(Contact.ICenterOrderIview iCenterOrderIview) {
        super(iCenterOrderIview);
    }

    @Override
    public void goBindingPhone(String Url) {
        Repostory.goBindingPhone(Url);
    }

    @Override
    public void goBindingPoint(String Url) {
        Repostory.goBindingPoint(Url);
    }


    @Override
    protected void createRepostory() {
        Repostory = new OrderRepostory();
    }
}
