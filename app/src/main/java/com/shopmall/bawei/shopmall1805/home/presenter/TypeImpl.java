package com.shopmall.bawei.shopmall1805.home.presenter;

import com.shopmall.bawei.framework.IView;
import com.shopmall.bawei.net.OkHttpHelper;
import com.shopmall.bawei.net.typebean.BugBean;
import com.shopmall.bawei.shopmall1805.home.contract.TypeContract;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TypeImpl extends TypeContract.TypePresenter {

    @Override
    public void UserShow(String url) {
        OkHttpHelper.getApi().getbug(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BugBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BugBean bugBean) {
                        iView.UserView(bugBean.getResult().get(0).getChild());
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
    public void UserReShow(String url) {
        OkHttpHelper.getApi().getbug(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BugBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BugBean bugBean) {
                        iView.UserRe(bugBean.getResult().get(0).getHot_product_list());
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
