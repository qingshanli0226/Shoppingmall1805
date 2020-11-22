package com.shopmall.bawei.framework;

public interface IView {
    void onError(String msg);
    void showLoading();
    void hideLoading();
}
