package com.shopmall.bawei.shopmall1805.user.presenter;

import com.bw.net.ForPayBean;
import com.bw.net.ForSendBean;
import com.bw.net.NetFunction;
import com.bw.net.RetraficCreator;
import com.bw.net.bean.Basebean;
import com.shopmall.bawei.shopmall1805.user.contract.UserContract;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class UserPresenter extends UserContract.IUserPresenter {


    @Override
    public void getForPay() {
        RetraficCreator.getiNetWorkApiService().findForPay()
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<Basebean<List<ForPayBean>>, List<ForPayBean>>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ForPayBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<ForPayBean> forPayBeanList) {
                        iView.onForPayOk(forPayBeanList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iView.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getForSend() {
        RetraficCreator.getiNetWorkApiService().findForSend()
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<Basebean<List<ForSendBean>>, List<ForSendBean>>())
                .subscribe(new Observer<List<ForSendBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<ForSendBean> forSendBeanList) {
                        iView.onForSendOK(forSendBeanList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iView.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
