package com.shopmall.bawei.shopmall1805.app.contract;

import com.shopmall.bawei.shopmall1805.common.BaseBean;
import com.shopmall.bawei.shopmall1805.common.HomeBean;
import com.shopmall.bawei.shopmall1805.framework.IModel;
import com.shopmall.bawei.shopmall1805.framework.IPresenter;
import com.shopmall.bawei.shopmall1805.framework.IView;


import io.reactivex.Observer;

public interface HomeContract {

    interface BannerHomeModel extends IModel{
        void getHomeData(Observer<HomeBean> observer);
    }
    abstract class BannerHomePresenter extends IPresenter<BannerHomeModel,BannerHomeView>{
        public BannerHomePresenter(BannerHomeView bannerHomeView) {
            super(bannerHomeView);
        }
        public abstract void getHomeDatas();
    }
    interface BannerHomeView extends IView{
        void setHomeData(HomeBean homeData);
    }
}
