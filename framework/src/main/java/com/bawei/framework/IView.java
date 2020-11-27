package com.bawei.framework;

import com.bawei.common.view.ErrorBean;

public interface IView {
    void onError(String msg);
    void showLoaDing();
    void hideLoading(boolean isSuccess, ErrorBean errorBean);
    void showEmpty();
}
