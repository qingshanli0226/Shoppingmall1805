package com.shopmall.bawei.shopmall1805.app.presenter;

import android.util.Log;

import com.shopmall.bawei.shopmall1805.app.contract.HomeContract;
import com.shopmall.bawei.shopmall1805.app.model.HomeModel;
import com.shopmall.bawei.shopmall1805.common.HomeBean;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class HomePresenter extends HomeContract.BannerHomePresenter {
    public HomePresenter(HomeContract.BannerHomeView bannerHomeView) {
        super(bannerHomeView);
    }
    @Override
    public void getHomeDatas() {
        if(iModel == null){
            Log.i("TAG", "getHomeDatas: ");
        }
        Log.i("TAG", "getHomeDatasssss: ");
            iModel.getHomeData(new Observer<HomeBean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }
                @Override
                public void onNext(HomeBean homeBean) {
                    if(homeBean!=null){
                        mView.get().setHomeData(homeBean);
                    }
                }
                @Override
                public void onError(Throwable e) {

                }
                @Override
                public void onComplete() {
                }
            });
    }
    @Override
    protected void createModel() {
        iModel = new HomeModel();
    }
}
