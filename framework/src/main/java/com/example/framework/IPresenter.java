package com.example.framework;

public interface IPresenter<T extends IVIew> {

    void attchView(T iView);
    void ondechView();
}
