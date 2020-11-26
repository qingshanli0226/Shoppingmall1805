package com.shopmall.bawei.shopmall1805.type.contract;

import android.util.Log;

import com.shopmall.bawei.common.ErrorBean;
import com.shopmall.bawei.common.ExceptionUtil;
import com.shopmall.bawei.net.NetFunction;
import com.shopmall.bawei.net.OkHttpHelper;
import com.shopmall.bawei.net.mode.BaseBean;
import com.shopmall.bawei.net.mode.HomeBean;
import com.shopmall.bawei.net.mode.TagBean;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class TagImpl extends TagContract.ITagPresenter {
    @Override
    public void tag() {
        OkHttpHelper.getApi()
                .getTag()
                .delay(1, TimeUnit.SECONDS)
                .map(new NetFunction<BaseBean<List<TagBean>>,List<TagBean>>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showLoaDing();
                    }
                })
                .subscribe(new Observer<List<TagBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<TagBean> tagBeans) {
                        if(tagBeans == null){
                            iView.showEmpty();
                        }else{
                            iView.onTag(tagBeans);
                            iView.hideLoading(true,null);
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
