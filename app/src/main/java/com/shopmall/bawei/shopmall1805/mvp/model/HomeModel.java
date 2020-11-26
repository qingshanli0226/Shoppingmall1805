package com.shopmall.bawei.shopmall1805.mvp.model;

import android.util.Log;

import com.shopmall.bawei.shopmall1805.api.Api;
import com.shopmall.bawei.shopmall1805.mvp.contract.HomeContract;

import http.MyHttp;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import baseurl.HomeBean;
import mvp.moudel.BaseModel;

public class HomeModel extends BaseModel implements HomeContract.getmodel {
    @Override
    public void sethome(Observer<HomeBean> observer) {
        Log.i("TAG1", "sethome: ");
        MyHttp.getInstance().retrofit()
                .create(Api.class)
                .gethome()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
