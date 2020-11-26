package com.shopmall.bawei.shopmall1805.mvp.prsenter;

import com.shopmall.bawei.shopmall1805.mvp.contract.LabelContract;

import java.util.List;

import baseurl.TagBean;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import mvp.presenter.BasePresenter;

public class LabelPresenter extends BasePresenter<LabelContract.getmodel, LabelContract.geteview> {
    public LabelPresenter(LabelContract.getmodel imoudel, LabelContract.geteview iview) {
        super(imoudel, iview);
    }

    public void inithomedata() {

        imoudel.sethome(new Observer<TagBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(TagBean skirstBean) {
                List<TagBean.ResultBean> result = skirstBean.getResult();
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