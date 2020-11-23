package framework.Mvp;

import java.lang.ref.SoftReference;

public abstract
class Presenter <R extends Repository,V extends Iview> {
    protected R Repostory;
    private SoftReference<V> iview;

    public Presenter(V v) {
        iview = new SoftReference<>(v);
        createRepostory();


    }

    protected abstract void createRepostory();



}
