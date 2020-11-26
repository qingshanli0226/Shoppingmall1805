package com.shopmall.bawei.shopmall1805.mvp.prsenter;

import android.util.Log;

import com.shopmall.bawei.shopmall1805.mvp.contract.HContract;
import com.shopmall.bawei.shopmall1805.mvp.contract.SContract;

import java.util.List;

import baseurl.HomeBean;
import baseurl.SkirstBean;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import mvp.presenter.BasePresenter;

public class SPresenter extends BasePresenter<SContract.getmodel, SContract.geteview> {
    public SPresenter(SContract.getmodel imoudel, SContract.geteview iview) {
        super(imoudel, iview);
    }

    public void inithomedata() {

        imoudel.sethome(iview.url(),new Observer<SkirstBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SkirstBean skirstBean) {
                List<SkirstBean.ResultBean> result = skirstBean.getResult();
                iview.getdata(result);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


    }
}