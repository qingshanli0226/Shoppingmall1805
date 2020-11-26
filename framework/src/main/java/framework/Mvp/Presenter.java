package framework.Mvp;

import java.lang.ref.SoftReference;

public abstract
class Presenter <R extends Repository,V extends Iview> {
    protected R Repostory;
    protected V view;
    private SoftReference<V> iview;

    public Presenter(V v) {
        iview = new SoftReference<>(v);
        this.view = v;
        createRepostory();


    }

    protected abstract void createRepostory();



}
