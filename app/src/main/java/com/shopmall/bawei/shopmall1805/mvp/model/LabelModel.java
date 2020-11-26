package com.shopmall.bawei.shopmall1805.mvp.model;

import com.shopmall.bawei.shopmall1805.api.Api;
import com.shopmall.bawei.shopmall1805.mvp.contract.LabelContract;

import baseurl.TagBean;
import http.MyHttp;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import mvp.moudel.BaseModel;

public class LabelModel extends BaseModel implements LabelContract.getmodel {


    @Override
    public void sethome(Observer<TagBean> observer) {
        MyHttp.getInstance().retrofit()
                .create(Api.class)
                .gettag()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
