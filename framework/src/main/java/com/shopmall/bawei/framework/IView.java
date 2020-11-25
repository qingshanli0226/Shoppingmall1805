package com.shopmall.bawei.framework;

import com.shopmall.bawei.common.ErrorBean;

public interface IView {
    //void showError(ErrorBean errorBean);//显示网络申请错误
    void onError(String msg);
    void showLoaDing();//网络加载的提示
    void hideLoading(boolean isSuccess, ErrorBean errorBean);//关闭网络加载的提示
    void showEmpty();
}
