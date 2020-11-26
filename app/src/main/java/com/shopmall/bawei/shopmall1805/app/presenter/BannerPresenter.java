package com.shopmall.bawei.shopmall1805.app.presenter;

import android.util.Log;

import com.shopmall.bawei.shopmall1805.app.contract.FirstHomeContract;
import com.shopmall.bawei.shopmall1805.app.model.BannerModel;
import com.shopmall.bawei.shopmall1805.common.BaseBean;
import com.shopmall.bawei.shopmall1805.common.HomeBean;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class BannerPresenter extends FirstHomeContract.BannerHomePresenter {
    public BannerPresenter(FirstHomeContract.BannerHomeView bannerHomeView) {
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
        iModel = new BannerModel();
    }
}
