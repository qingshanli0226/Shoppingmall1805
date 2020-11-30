package com.shopmall.bawei.shopmall1805.home;


import com.example.net.RetrofitCreater;
import com.example.net.bean.MainBean;
import com.shoppmall.common.adapter.error.ExceptionUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomePresenterImpl extends HomeContract.HomePresenter {
    @Override
    public void loadMain() {
        RetrofitCreater.getiNetWorkApi()
                .loadMain()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iview.showloading();
                    }
                })
                .subscribe(new Observer<MainBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MainBean bean) {
                        iview.hideLoading(true,null);
                        if(bean.getResult()!=null){
                            iview.onOk(bean);
                        }else {
                            iview.showEmpty();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iview.hideLoading(false, ExceptionUtil.getErrorBean(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
