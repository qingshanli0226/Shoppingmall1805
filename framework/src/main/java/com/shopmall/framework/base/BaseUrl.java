package com.shopmall.framework.base;

import com.shopmall.framework.mvp.Presenter;

public abstract class BaseUrl<P extends Presenter> {
    protected P mPresenter;

    protected abstract void createPresenter();

    public BaseUrl(){
        createPresenter();
    }
}
