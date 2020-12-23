package com.bawei.order.contact;

import com.bawei.common.view.ExceptionUtil;
import com.bawei.net.RetrofitCreate;
import com.bawei.net.mode.FindForPayBean;
import com.bawei.net.mode.FindForSendBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FindForOrderContactImpl extends FindForOrderContact.IFindForOrderPresenter {
    @Override
    public void FindForPay() {
        RetrofitCreate.getApi().getFindForPay()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FindForPayBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FindForPayBean findForPayBean) {
                        iView.onFindForPay(findForPayBean);
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

    @Override
    public void FindForSend() {
        RetrofitCreate.getApi().getFindForSend()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FindForSendBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FindForSendBean findForSendBean) {
                        iView.onFindForSend(findForSendBean);
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
