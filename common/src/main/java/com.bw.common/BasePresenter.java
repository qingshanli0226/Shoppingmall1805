package com.bw.common;


public class BasePresenter<V extends IView> implements IPresenter<V> {

    protected V iView ;

    @Override
    public void accatchView(IView iView) {
        this.iView = (V) iView;
    }

    @Override
    public void detachView() {
        iView = null;
    }
}
