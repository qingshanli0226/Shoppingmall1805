package com.example.framework.mvp;

public interface IPresenter<V extends IView> {
    void attchView(V iview);
    void detachView();
}
