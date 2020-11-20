package com.shopmall.bawei.shopmall1805.framework;

import java.lang.ref.SoftReference;

public abstract class IPresenter<M extends IModel,V extends IView> {

    protected M iModel;

    private SoftReference<V> iView;

    public IPresenter(V v) {
        createModel();
        iView = new SoftReference<>(v);
    }
    protected abstract void createModel();
}
