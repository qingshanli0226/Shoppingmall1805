package com.shopmall.bawei.shopmall1805.home;


import com.example.net.RetrofitCreater;
import com.example.net.bean.MainBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePresenterImpl extends HomeContract.HomePresenter {
    @Override
    public void loadMain() {
        RetrofitCreater.getiNetWorkApi()
                .loadMain()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MainBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MainBean bean) {
                        iview.onOk(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iview.onEorror(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
