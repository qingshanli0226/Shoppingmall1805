package com.bawei.shopmall.home.contract;

import com.bawei.common.view.ErrorBean;
import com.bawei.common.view.ExceptionUtil;
import com.bawei.net.RetrofitCreate;
import com.bawei.net.mode.HomeBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomeImpl extends HomeContract.HomePresenter {
    @Override
    public void getHomeData() {
        RetrofitCreate.getApi().getHomeData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showLoaDing();
                    }
                })
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeBean homeBean) {
                        if (homeBean == null) {
                            iView.showEmpty();
                        } else {
                            iView.hideLoading(true, null);
                            iView.onHomeData(homeBean);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        ErrorBean errorBean = ExceptionUtil.getErrorBean(e);
                        iView.hideLoading(false, errorBean);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
