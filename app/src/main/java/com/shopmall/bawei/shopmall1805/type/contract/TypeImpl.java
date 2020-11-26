package com.shopmall.bawei.shopmall1805.type.contract;

import android.util.Log;

import com.shopmall.bawei.common.ErrorBean;
import com.shopmall.bawei.common.ExceptionUtil;
import com.shopmall.bawei.net.MyNetApi;
import com.shopmall.bawei.net.NetFunction;
import com.shopmall.bawei.net.OkHttpHelper;
import com.shopmall.bawei.net.mode.BaseBean;
import com.shopmall.bawei.net.mode.TagBean;
import com.shopmall.bawei.net.mode.TypeBean;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class TypeImpl extends TypeContract.ITypePresenter {

    @Override
    public MyNetApi type() {
        return OkHttpHelper.getApi();
    }

    public void getSkirt() {
        type().getSkirt()
                .map(new NetFunction<BaseBean<List<TypeBean>>,List<TypeBean>>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showLoaDing();
                    }
                })
                .subscribe(new Observer<List<TypeBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<TypeBean> typeBeans) {
                        if (typeBeans == null) {
                            iView.showEmpty();
                        } else {
                            iView.onType(typeBeans.get(0));
                            iView.hideLoading(true, null);
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
    public void getJacket(){
        type().getJacket()
                .map(new NetFunction<BaseBean<List<TypeBean>>,List<TypeBean>>())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showLoaDing();
                    }
                })
            .subscribe(new Observer<List<TypeBean>>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(List<TypeBean> typeBeans) {
                    if (typeBeans == null) {
                        iView.showEmpty();
                    } else {
                        iView.onType(typeBeans.get(0));
                        iView.hideLoading(true, null);
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
            });}
    public void getPants(){type().getPants()
            .map(new NetFunction<BaseBean<List<TypeBean>>,List<TypeBean>>())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe(new Consumer<Disposable>() {
                @Override
                public void accept(Disposable disposable) throws Exception {
                    iView.showLoaDing();
                }
            })
            .subscribe(new Observer<List<TypeBean>>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(List<TypeBean> typeBeans) {
                    if (typeBeans == null) {
                        iView.showEmpty();
                    } else {
                        iView.onType(typeBeans.get(0));
                        iView.hideLoading(true, null);
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
            });}
    public void getOvercoat(){type().getOvercoat()
            .map(new NetFunction<BaseBean<List<TypeBean>>,List<TypeBean>>())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe(new Consumer<Disposable>() {
                @Override
                public void accept(Disposable disposable) throws Exception {
                    iView.showLoaDing();
                }
            })
            .subscribe(new Observer<List<TypeBean>>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(List<TypeBean> typeBeans) {
                    if (typeBeans == null) {
                        iView.showEmpty();
                    } else {
                        iView.onType(typeBeans.get(0));
                        iView.hideLoading(true, null);
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
            });}
    public void getAccessory(){type().getAccessory()
            .map(new NetFunction<BaseBean<List<TypeBean>>,List<TypeBean>>())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe(new Consumer<Disposable>() {
                @Override
                public void accept(Disposable disposable) throws Exception {
                    iView.showLoaDing();
                }
            })
            .subscribe(new Observer<List<TypeBean>>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(List<TypeBean> typeBeans) {
                    if (typeBeans == null) {
                        iView.showEmpty();
                    } else {
                        iView.onType(typeBeans.get(0));
                        iView.hideLoading(true, null);
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
            });}
    public void getBag(){type().getBag()
            .map(new NetFunction<BaseBean<List<TypeBean>>,List<TypeBean>>())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe(new Consumer<Disposable>() {
                @Override
                public void accept(Disposable disposable) throws Exception {
                    iView.showLoaDing();
                }
            })
            .subscribe(new Observer<List<TypeBean>>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(List<TypeBean> typeBeans) {
                    if (typeBeans == null) {
                        iView.showEmpty();
                    } else {
                        iView.onType(typeBeans.get(0));
                        iView.hideLoading(true, null);
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
            });}
    public void getDress(){type().getDress()
            .map(new NetFunction<BaseBean<List<TypeBean>>,List<TypeBean>>())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe(new Consumer<Disposable>() {
                @Override
                public void accept(Disposable disposable) throws Exception {
                    iView.showLoaDing();
                }
            })
            .subscribe(new Observer<List<TypeBean>>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(List<TypeBean> typeBeans) {
                    if (typeBeans == null) {
                        iView.showEmpty();
                    } else {
                        iView.onType(typeBeans.get(0));
                        iView.hideLoading(true, null);
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
            });}
    public void getProduct(){type().getProduct()
            .map(new NetFunction<BaseBean<List<TypeBean>>,List<TypeBean>>())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe(new Consumer<Disposable>() {
                @Override
                public void accept(Disposable disposable) throws Exception {
                    iView.showLoaDing();
                }
            })
            .subscribe(new Observer<List<TypeBean>>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(List<TypeBean> typeBeans) {
                    if (typeBeans == null) {
                        iView.showEmpty();
                    } else {
                        iView.onType(typeBeans.get(0));
                        iView.hideLoading(true, null);
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
            });}
    public void getStationery(){type().getStationery()
            .map(new NetFunction<BaseBean<List<TypeBean>>,List<TypeBean>>())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe(new Consumer<Disposable>() {
                @Override
                public void accept(Disposable disposable) throws Exception {
                    iView.showLoaDing();
                }
            })
            .subscribe(new Observer<List<TypeBean>>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(List<TypeBean> typeBeans) {
                    if (typeBeans == null) {
                        iView.showEmpty();
                    } else {
                        iView.onType(typeBeans.get(0));
                        iView.hideLoading(true, null);
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
            });}
    public void getDigit(){type().getDigit()
            .map(new NetFunction<BaseBean<List<TypeBean>>,List<TypeBean>>())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe(new Consumer<Disposable>() {
                @Override
                public void accept(Disposable disposable) throws Exception {
                    iView.showLoaDing();
                }
            })
            .subscribe(new Observer<List<TypeBean>>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(List<TypeBean> typeBeans) {
                    if (typeBeans == null) {
                        iView.showEmpty();
                    } else {
                        iView.onType(typeBeans.get(0));
                        iView.hideLoading(true, null);
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
            });}
    public void getGame(){type().getGame()
            .map(new NetFunction<BaseBean<List<TypeBean>>,List<TypeBean>>())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe(new Consumer<Disposable>() {
                @Override
                public void accept(Disposable disposable) throws Exception {
                    iView.showLoaDing();
                }
            })
            .subscribe(new Observer<List<TypeBean>>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(List<TypeBean> typeBeans) {
                    if (typeBeans == null) {
                        iView.showEmpty();
                    } else {
                        iView.onType(typeBeans.get(0));
                        iView.hideLoading(true, null);
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
            });}

}
