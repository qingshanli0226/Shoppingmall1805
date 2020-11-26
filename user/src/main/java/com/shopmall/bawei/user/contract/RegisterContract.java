package com.shopmall.bawei.user.contract;

import com.example.framework.BaseIPresenter;
import com.example.framework.IVIew;
import com.example.net.JavaBean;

public interface RegisterContract {

    interface RegisterView extends IVIew {
        void getViewData1(JavaBean clotheslist);
    }

    abstract class RegisterPresenter extends BaseIPresenter<RegisterContract.RegisterView> {

        public abstract void getData1();
    }

}
