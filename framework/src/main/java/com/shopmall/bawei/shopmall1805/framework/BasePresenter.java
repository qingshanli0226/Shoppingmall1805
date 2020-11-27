package com.shopmall.bawei.shopmall1805.framework;

import java.lang.ref.SoftReference;

public class BasePresenter<V extends IView> implements IPresenter<V> {
    protected V iHttpView;
    @Override
    public void attachView(V iHttpView) {
        this.iHttpView = iHttpView;
    }
    @Override
    public void detachView() {
        this.iHttpView = null;
    }
}
