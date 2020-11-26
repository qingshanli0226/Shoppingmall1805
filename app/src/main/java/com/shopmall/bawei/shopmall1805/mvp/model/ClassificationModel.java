package com.shopmall.bawei.shopmall1805.mvp.model;

import com.shopmall.bawei.shopmall1805.api.Api;
import com.shopmall.bawei.shopmall1805.mvp.contract.ClassificationContract;

import baseurl.SkirstBean;
import http.MyHttp;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import mvp.moudel.BaseModel;

public class ClassificationModel extends BaseModel implements ClassificationContract.getmodel {


    @Override
    public void sethome(String url, Observer<SkirstBean> observer) {
        MyHttp.getInstance().retrofit()
                .create(Api.class)
                .getskirt(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
