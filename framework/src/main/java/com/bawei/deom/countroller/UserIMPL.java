package com.bawei.deom.countroller;

import android.util.Log;

import com.bawei.deom.ClassInterface;
import com.bawei.deom.view.LoadingPage;


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
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class UserIMPL extends UserCountroller.UserShow {


    @Override
    public void getskerak(final LoadingPage loadingPage) {
        ClassInterface.getUserInterface().home()
                .delay(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                              loadingPage.showLoadingPage();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new Observer<BaseBean<HomeBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean<HomeBean> homeBeanBaseBean) {
                        HomeBean result = homeBeanBaseBean.getResult();

                            pView.onskerk(result);
                            loadingPage.showSuccessView();

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
    public void TagShow(final LoadingPage loadingPage) {
        ClassInterface.getUserInterface().tag()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        pView.loading();
                        loadingPage.showLoadingPage();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        pView.hideloading();

                    }
                })
                .subscribe(new Observer<TAGBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TAGBean tagBean) {
                               pView.onTagBiew(tagBean.getResult());
                               loadingPage.showSuccessView();
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
