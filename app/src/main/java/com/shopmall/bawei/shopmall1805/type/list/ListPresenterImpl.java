package com.shopmall.bawei.shopmall1805.type.list;

import com.example.net.RetrofitCreater;
import com.example.net.bean.GoodsBean;
import com.shoppmall.common.adapter.error.ExceptionUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ListPresenterImpl extends ListContract.ListPresenter {
    @Override
    public void showGoods(String url) {
        RetrofitCreater.getiNetWorkApi()
                .showGoods(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iview.showloading();
                    }
                })
                .subscribe(new Observer<GoodsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GoodsBean bean) {
                        iview.hideLoading(true,null);
                        if(bean.getResult()!=null){
                            iview.onListOK(bean);
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
