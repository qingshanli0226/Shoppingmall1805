package com.shopmall.bawei.shopmall1805.app.model;

import com.shopmall.bawei.shopmall1805.app.contract.FenleiContract;
import com.shopmall.bawei.shopmall1805.app.contract.FenleiVpTwoContract;
import com.shopmall.bawei.shopmall1805.common.ClothesBean;
import com.shopmall.bawei.shopmall1805.common.ConfigUrl;
import com.shopmall.bawei.shopmall1805.common.FenLeiVpTwoEntity;
import com.shopmall.bawei.shopmall1805.common.INetPresetenterWork;
import com.shopmall.bawei.shopmall1805.net.RetrofitUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FenLeiTwoModel implements FenleiVpTwoContract.FenLeiTwoModel {

    @Override
    public void destroy() {

    }
    @Override
    public void getFenData(Observer<FenLeiVpTwoEntity> observer) {
        RetrofitUtils.getInstance().getRetrofit(ConfigUrl.BASE)
                .create(INetPresetenterWork.class)
                .tag()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
