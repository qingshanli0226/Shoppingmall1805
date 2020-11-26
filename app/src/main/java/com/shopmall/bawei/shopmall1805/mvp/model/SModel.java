package com.shopmall.bawei.shopmall1805.mvp.model;

import android.util.Log;

import com.shopmall.bawei.shopmall1805.api.FirstApi;
import com.shopmall.bawei.shopmall1805.mvp.contract.HContract;
import com.shopmall.bawei.shopmall1805.mvp.contract.SContract;

import baseurl.HomeBean;
import baseurl.SkirstBean;
import http.MyHttp;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import mvp.moudel.BaseModel;

public class SModel extends BaseModel implements SContract.getmodel {


    @Override
    public void sethome(String url, Observer<SkirstBean> observer) {
        MyHttp.getInstance().retrofit()
                .create(FirstApi.class)
                .getskirt(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
