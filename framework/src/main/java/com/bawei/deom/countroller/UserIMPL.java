package com.bawei.deom.countroller;

import android.util.Log;

import com.bawei.deom.ClassInterface;


import java.util.List;
import java.util.concurrent.TimeUnit;

import bean.BaseBean;
import bean.ClothesBean;
import bean.HomeBean;
import bean.TAGBean;
import bean.typebean.SkirtBean;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserIMPL extends UserCountroller.UserShow {


    @Override
    public void getskerak() {
        ClassInterface.getUserInterface().home()
                .delay(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())

                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean<HomeBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean<HomeBean> homeBeanBaseBean) {
                        HomeBean result = homeBeanBaseBean.getResult();

                            pView.onskerk(result);
                            pView.showSuccessView2();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void TagShow() {
        ClassInterface.getUserInterface().tag()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TAGBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TAGBean tagBean) {
                               pView.TagBiew(tagBean.getResult());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        ;
    }


}
