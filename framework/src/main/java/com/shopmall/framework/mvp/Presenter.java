package com.shopmall.framework.mvp;

import java.lang.ref.SoftReference;

public abstract class Presenter<V extends IView,R extends Repository> {

    protected R mRepository;

    protected SoftReference<V> mView;

    protected abstract void createRepository();

    public Presenter(V v){
        createRepository();
        mView = new SoftReference<>(v);
    }
}
