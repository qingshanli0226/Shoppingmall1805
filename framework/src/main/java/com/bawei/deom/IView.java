package com.bawei.deom;

public interface IView {
    void loading();
    void hideloading();
    void showLoadingPage2();
    void showErrorPage2(String errorMsg);
    void showEmptyPage2();
    void showSuccessView2();
}
