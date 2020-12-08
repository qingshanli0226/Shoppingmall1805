package com.shopmall.framework.mvptest.model;

import com.shopmall.framework.callback.Home;
import com.shopmall.framework.constart.Constart;
import com.shopmall.framework.view.LoadingPage;
import com.shopmall.net.Https;
import com.shopmall.net.RetrofitFactory;
import com.shopmall.net.bean.HomeData;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomeModel implements Constart.HomeConstartModel {

    @Override
    public void homec(String url, final LoadingPage loadingPage, final Home home) {
        Https getinstance = RetrofitFactory.getInstance().create(Https.class);
        getinstance.getonser(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        loadingPage.showLoadingPage();
                    }
                })
                .subscribe(new Observer<HomeData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeData homeData) {
                        loadingPage.showSuccessView();
                            home.Ok(homeData);
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadingPage.showSuccessView();
                        home.No(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void destroy() {

    }
}
