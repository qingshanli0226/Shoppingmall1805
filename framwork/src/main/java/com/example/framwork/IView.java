package com.example.framwork;

public interface   IView {
    void onError(String code,String message);
    void showLoading();//网络显示
    void hideLoading(boolean isSuccess,String message);//隐藏加载
}
