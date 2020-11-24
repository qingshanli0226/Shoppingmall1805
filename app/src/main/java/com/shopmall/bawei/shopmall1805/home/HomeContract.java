package com.shopmall.bawei.shopmall1805.home;

import com.example.framework.base.BasePresenter;
import com.example.framework.mvp.IView;
import com.example.net.bean.MainBean;

public interface HomeContract {
    public interface HomeView extends IView {
        void onOk(MainBean bean);
        void onEorror(String msg);
    }

    public static abstract class HomePresenter extends BasePresenter<HomeView> {
        public abstract void loadMain();
    }
}
