package com.shopmall.bawei.shopmall1805.home.contract;

import com.example.net.bean.HomeBean;
import com.shopmall.bawei.framework.example.framework.BasePresenter;
import com.shopmall.bawei.framework.example.framework.IView;


public class HomeContract {
    public interface SkerakView extends IView {
        void onskerk(HomeBean homeBeanList);
    }
    public abstract static  class SlerakPresenter extends BasePresenter<SkerakView> {
        public abstract void getskerak();
    }
}
