package com.shopmall.bawei.framework;

import com.shopmall.bawei.common.ErrorBean;

//定义一个通用的接口
public interface IView {
    //void showError(ErrorBean errorBean);//显示网络申请错误
    void showLoaing();//网络加载的提示
    void hideLoading(boolean isSuccess, ErrorBean errorBean);//关闭网络加载的提示
    void showEmpty();
}
