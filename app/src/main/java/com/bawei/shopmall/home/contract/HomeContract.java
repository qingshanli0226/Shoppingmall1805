package com.bawei.shopmall.home.contract;

import com.bawei.framework.base.BasePresenter;
import com.bawei.framework.base.IView;
import com.bawei.net.bean.HomeBean;

public class HomeContract {
    public interface IHomeView extends IView {
        void onHomeData(HomeBean homeBean);
    }
    public static abstract class HomePresenter extends BasePresenter<IHomeView> {
        public abstract void getHomeData();
    }
}
