package com.shopmall.bawei.shopmall1805.app.model;

import com.shopmall.bawei.shopmall1805.app.contract.HomeContract;
import com.shopmall.bawei.shopmall1805.common.ConfigUrl;
import com.shopmall.bawei.shopmall1805.common.HomeBean;
import com.shopmall.bawei.shopmall1805.common.INetPresetenterWork;
import com.shopmall.bawei.shopmall1805.net.RetrofitUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeModel implements HomeContract.BannerHomeModel {

    @Override
    public void destroy() {

    }
    @Override
    public void getHomeData(Observer<HomeBean> observer) {
        RetrofitUtils.getInstance().getRetrofit(ConfigUrl.BASE_URL)
                .create(INetPresetenterWork.class)
                .home()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
