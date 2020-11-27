package com.shopmall.bawei.shopmall1805.app.presenter;

import com.shopmall.bawei.shopmall1805.app.contract.HomeContract;
import com.shopmall.bawei.shopmall1805.common.HomeBean;
import com.shopmall.bawei.shopmall1805.net.BaseObserver;
import com.shopmall.bawei.shopmall1805.net.RetrofitUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomePresenterImpl extends HomeContract.HomePresenter {

    @Override
    public void getHomeData() {
        RetrofitUtils.getiNetPresetenterWork()
                .home()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<HomeBean>() {
                    @Override
                    public void onNext(HomeBean homeBean) {
                        if(homeBean!=null){
                            iHttpView.onHomeDatas(homeBean);
                        }
                    }
                });
    }
}
