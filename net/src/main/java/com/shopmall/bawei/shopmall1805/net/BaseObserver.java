package com.shopmall.bawei.shopmall1805.net;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class BaseObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {
    }
    @Override
    public void onNext(T t) {
    }
    @Override
    public void onError(Throwable e) {
    }
    @Override
    public void onComplete() {
    }
}
