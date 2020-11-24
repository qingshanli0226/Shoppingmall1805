package com.shopmall.framework.mvp;

import java.lang.ref.SoftReference;

public abstract class IPresenter<V extends IView,R extends Repository> {

    protected R mRepository;

    protected SoftReference<V> mView;

    protected abstract void createRepository();

    public IPresenter(V v){
        createRepository();
        mView = new SoftReference<>(v);
    }
}
