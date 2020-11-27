package com.example.framework.mvp;

import com.shoppmall.common.adapter.error.ErrorBean;

public interface IView {
    void showloading();
    void hideLoading(boolean isSuccess, ErrorBean errorBean);//关闭网络加载的提示
    void showEmpty();
}
