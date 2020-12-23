package com.bawei.order.contact;

import com.bawei.framework.BasePresenter;
import com.bawei.framework.IView;
import com.bawei.net.mode.FindForPayBean;
import com.bawei.net.mode.FindForSendBean;

public class FindForOrderContact {

    public interface IFindForOrderView extends IView {
        void onFindForPay(FindForPayBean findForPayBean);

        void onFindForSend(FindForSendBean findForSendBean);
    }

    public static abstract class IFindForOrderPresenter extends BasePresenter<IFindForOrderView> {
        public abstract void FindForPay();

        public abstract void FindForSend();
    }
}
