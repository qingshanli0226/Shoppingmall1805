package com.bawei.order.contact;

import com.bawei.framework.BasePresenter;
import com.bawei.framework.IView;

public class BanDingUserContact {
    public interface IBanDIngView extends IView {
        void onBanDingPhone(String message);

        void onBanDingAddress(String message);
    }

    public static abstract class IBanDingPresenter extends BasePresenter<IBanDIngView> {
        public abstract void BanDingPhone(String phone);

        public abstract void BanDingAddress(String address);
    }
}
