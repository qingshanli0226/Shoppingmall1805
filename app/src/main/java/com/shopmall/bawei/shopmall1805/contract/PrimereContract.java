package com.shopmall.bawei.shopmall1805.contract;

import com.example.framework.BaseIPresenter;
import com.example.framework.IVIew;
import com.example.net.BaseBean;
import com.example.net.HomeBean;

public interface PrimereContract {

    interface PrimereView extends IVIew {
        void getViewData(BaseBean<HomeBean> clotheslist);
    }

    abstract class PrimerePresenter extends BaseIPresenter<PrimereView> {
        public abstract void getData();

    }

}
