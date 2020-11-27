package com.shopmall.bawei.shopmall1805.framework;

import android.os.Bundle;

import androidx.annotation.Nullable;

public abstract class BaseMVPActivity<T extends IPresenter,V extends IView> extends BaseActivity {
    protected T httpPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
        httpPresenter.attachView((V)this);
        initData();
    }
    protected abstract void initData();

    protected abstract void initPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        httpPresenter.detachView();
    }

}
