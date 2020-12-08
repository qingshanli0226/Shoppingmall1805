package com.bw.framework;

public interface IPresenter<V extends IView> {

    void accatchView(IView iView);
    void detachView();

}
