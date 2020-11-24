package com.shopmall.bawei.shopmall1805.type.list;

import com.example.net.RetrofitCreater;
import com.example.net.bean.GoodsBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ListPresenterImpl extends ListContract.ListPresenter {
    @Override
    public void showGoods(String url) {
        RetrofitCreater.getiNetWorkApi()
                .showGoods(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GoodsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GoodsBean bean) {
                        iview.onListOK(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iview.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
