package com.bawei.framework.base;

public interface IPresenter<V extends IView> {
    void attachView(V iView);
    void detachView();
}
