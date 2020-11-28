package com.example.framework;

public interface IPresenter<T extends IView> {
    void attchView(T iView);
    void ondechView();
}
