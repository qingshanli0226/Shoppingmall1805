package com.shopmall.bawei.shopmall1805.home.contract;


import com.bw.common.BasePresenter;
import com.bw.common.IView;
import com.bw.net.bean.HomeFragmentBean;


public class HomeContract {

    public interface IHomeView extends IView {
       void onOk(HomeFragmentBean homeFragmentBean);
       void onError(String message);
    }

    public abstract static class HomePresenter extends BasePresenter<IHomeView> {
        public abstract void onGetHomeData();
    }
}
