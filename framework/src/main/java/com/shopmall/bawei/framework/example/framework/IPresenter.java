package com.shopmall.bawei.framework.example.framework;

public interface IPresenter<T extends IView>{
    void attchView(T iView);
    void ondechView();
}
