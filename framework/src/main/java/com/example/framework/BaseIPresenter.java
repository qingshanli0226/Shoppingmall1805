package com.example.framework;

public class BaseIPresenter<T extends IVIew> implements IPresenter<T>  {

    protected T iView;

    @Override
    public void attchView(T iView) {
        this.iView = iView;
    }

    @Override
    public void ondechView() {
        iView = null;
    }
}
