package com.example.framework.base;

import com.example.framework.mvp.IPresenter;
import com.example.framework.mvp.IView;

public class BasePresenter<V extends IView> implements IPresenter<V> {
    protected V iview;

    @Override
    public void attchView(V iview) {
        this.iview=iview;
    }

    @Override
    public void detachView() {
        iview=null;
    }
}
