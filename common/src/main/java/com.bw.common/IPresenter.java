package com.bw.common;

public interface IPresenter<V extends IView> {

    void accatchView(IView iView);
    void detachView();

}
