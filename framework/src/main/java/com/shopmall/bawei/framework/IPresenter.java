package com.shopmall.bawei.framework;

public interface IPresenter<V extends IView> {
    void attachView(V iView);
    void detachView();
}
