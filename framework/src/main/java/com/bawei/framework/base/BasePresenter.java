package com.bawei.framework.base;

public class BasePresenter<V extends IView> implements IPresenter<V> {
    protected V iView;

    @Override
    public void attachView(V iView) {
        this.iView = iView;
    }

    @Override
    public void detachView() {
        iView = null;
    }
}
