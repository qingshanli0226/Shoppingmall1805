package com.shopmall.bawei.shopmall1805.home.contract;

import com.example.common2.HomeBean;

import mvp.view.BasePresenter;
import mvp.view.IView;

public class HomeContract {
    public interface IHomeView extends IView {
        void onHomeData(HomeBean homeBean);
    }

    public static abstract class HomePresenter extends BasePresenter<IHomeView>{
        public abstract  void getHomeData();
    }
}
