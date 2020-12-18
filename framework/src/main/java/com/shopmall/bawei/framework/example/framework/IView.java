package com.shopmall.bawei.framework.example.framework;

public interface IView {
    void onErroy(String message);
    void showsloading();
    void hideloading();
    void showErroy(String message);
    void showEmpty();
}
