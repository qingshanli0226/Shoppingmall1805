package com.shopmall.bawei.shopmall1805.user.contract;

import com.shopmall.bawei.common.ExceptionUtil;
import com.shopmall.bawei.net.NetFunction;
import com.shopmall.bawei.net.OkHttpHelper;
import com.shopmall.bawei.net.mode.BaseBean;
import com.shopmall.bawei.net.mode.PayBean;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PayImpl extends IPayContract.IPayPresenter {
    @Override
    public void getPayBeans() {
        OkHttpHelper.getApi().findForPay()
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<List<PayBean>>,List<PayBean>>())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showLoaDing();
                    }
                })
                .subscribe(new Observer<List<PayBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<PayBean> payBeanList) {
                        iView.onPay(payBeanList);
                        iView.hideLoading(true,null);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iView.hideLoading(false, ExceptionUtil.getErrorBean(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
