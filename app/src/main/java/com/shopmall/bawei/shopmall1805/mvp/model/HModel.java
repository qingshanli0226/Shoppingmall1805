package com.shopmall.bawei.shopmall1805.mvp.model;

import android.util.Log;

import com.shopmall.bawei.shopmall1805.api.FirstApi;
import com.shopmall.bawei.shopmall1805.mvp.contract.HContract;

import http.MyHttp;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import baseurl.HomeBean;
import mvp.moudel.BaseModel;

public class HModel extends BaseModel implements HContract.getmodel {
    @Override
    public void sethome(Observer<HomeBean> observer) {
        Log.i("TAG1", "sethome: ");
        MyHttp.getInstance().retrofit()
                .create(FirstApi.class)
                .gethome()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
