package com.shopmall.bawei.shopmall1805.framework;


import androidx.lifecycle.LifecycleOwner;

public interface IView {
    //void showError(ErrorBean errorBean);//显示网络申请错误
    void showLoaing();//网络加载的提示
    void hideLoading();//关闭网络加载的提示
    void showEmpty();
}
