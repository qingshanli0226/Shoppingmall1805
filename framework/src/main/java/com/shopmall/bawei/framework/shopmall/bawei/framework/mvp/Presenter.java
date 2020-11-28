package com.shopmall.bawei.framework.shopmall.bawei.framework.mvp;

import java.lang.ref.SoftReference;

public abstract class Presenter<V extends Iview,R extends Repository> {
       protected R mRepository;
    /**
     * 防止v层内存泄漏
     */
    protected SoftReference<V> mView;

    protected abstract void createRepository();


    public Presenter(V v){
         mView=new SoftReference<>(v);
         createRepository();
    }


}
