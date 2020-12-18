package com.shopmall.bawei.framework.example.framework;

public class BasePresenter<V extends IView> implements IPresenter<V> {
    protected V iView;

    @Override
    public void attchView(V iView) {
        this.iView = iView;
    }

    @Override
    public void ondechView() {
        iView = null;
    }
}
