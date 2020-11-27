package com.shopmall.bawei.shopmall1805.app.contract;

import com.shopmall.bawei.shopmall1805.common.HomeBean;
import com.shopmall.bawei.shopmall1805.framework.BasePresenter;
import com.shopmall.bawei.shopmall1805.framework.IView;


import io.reactivex.Observer;

public class HomeContract {
    public interface IHomeView extends IView {
        void onHomeDatas(HomeBean homeBean);
    }
    public static abstract class HomePresenter extends BasePresenter<IHomeView> {
        public abstract void getHomeData();
    }
}
