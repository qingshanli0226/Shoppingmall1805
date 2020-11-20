package com.shopmall.bawei.shopmall1805.base;

public interface IPresenter<V extends IView> {

    void accatchView(IView iView);
    void detachView();

}
