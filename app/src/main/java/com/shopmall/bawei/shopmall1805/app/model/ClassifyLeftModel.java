package com.shopmall.bawei.shopmall1805.app.model;

import com.shopmall.bawei.shopmall1805.app.contract.ClassifyLeftContract;
import com.shopmall.bawei.shopmall1805.common.ClothesBean;
import com.shopmall.bawei.shopmall1805.common.ConfigUrl;
import com.shopmall.bawei.shopmall1805.common.INetPresetenterWork;
import com.shopmall.bawei.shopmall1805.net.RetrofitUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ClassifyLeftModel implements ClassifyLeftContract.FenLeiModel {

    @Override
    public void destroy() {

    }
    @Override
    public void getFenData(String url, Observer<ClothesBean> observer) {
        RetrofitUtils.getInstance().getRetrofit(ConfigUrl.BASE)
                .create(INetPresetenterWork.class)
                .skirt(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
