package com.shopmall.bawei.order.presenter;

import com.shopmall.bawei.order.mview.UnpaidActivity;
import com.shopmall.bawei.order.repostory.OrderRepostory;

import framework.Contact;
import framework.IOrderData;
import view.loadinPage.UnpaidBean;

public
class OrderPresenter extends Contact.CenterOrderPresenter {
    private UnpaidActivity unpaidActivity;

    public OrderPresenter(Contact.ICenterOrderIview iCenterOrderIview) {
        super(iCenterOrderIview);
        this.unpaidActivity = (UnpaidActivity) iCenterOrderIview;
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
    public void goUnpaidOrder() {
            Repostory.goUnpaidOrder(new IOrderData() {
                @Override
                public void UnpaidBean(UnpaidBean e) {
                    unpaidActivity.onUnpaidSuccess(e);
                }

                @Override
                public void Error(String e) {
                    unpaidActivity.onError(e);
                }
            });
    }


    @Override
    protected void createRepostory() {
        Repostory = new OrderRepostory();
    }
}
