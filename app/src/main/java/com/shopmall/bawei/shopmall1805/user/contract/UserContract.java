package com.shopmall.bawei.shopmall1805.user.contract;

import com.bw.framework.BasePresenter;
import com.bw.framework.IView;
import com.bw.net.ForPayBean;
import com.bw.net.ForSendBean;

import java.util.List;

public class UserContract {

    public interface UserView extends IView {
        void onForPayOk(List<ForPayBean> forPayBeanList);
        void onForSendOK(List<ForSendBean> forSendBeanList);
    }

    public abstract static class IUserPresenter extends BasePresenter<UserView>{
        public abstract void getForPay();
        public abstract void getForSend();
    }

}
