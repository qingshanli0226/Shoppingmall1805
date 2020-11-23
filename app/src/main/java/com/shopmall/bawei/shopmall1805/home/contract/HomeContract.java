package com.shopmall.bawei.shopmall1805.home.contract;


import com.bw.net.bean.HomeFragmentBean;
import com.shopmall.bawei.shopmall1805.base.BasePresenter;
import com.shopmall.bawei.shopmall1805.base.IView;

public class HomeContract {

    public interface IHomeView extends IView {
       void onOk(HomeFragmentBean homeFragmentBean);
       void onError(String message);
    }

    public abstract static class HomePresenter extends BasePresenter<IHomeView> {
        public abstract void onGetHomeData();
    }
}
