package com.bawei.shopmall.home.contract;

import com.bawei.framework.BasePresenter;
import com.bawei.framework.IView;
import com.bawei.net.mode.HomeBean;

public class HomeContract {
    public interface IHomeView extends IView {
        void onHomeData(HomeBean homeBean);
    }
    public static abstract class HomePresenter extends BasePresenter<IHomeView> {
        public abstract void getHomeData();
    }
}
