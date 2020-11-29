package com.shopmall.bawei.framework;

import com.shopmall.bawei.common.ErrorBean;

public interface IView {
    void onError(String msg);
    void showLoading();
    void hideLoading(boolean isSuccess, ErrorBean errorBean);
}
