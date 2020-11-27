package com.shopmall.bawei.shopmall1805.framework;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class BaseMVPFragment <T extends IPresenter, V extends IView> extends BaseFragment {

    protected T ihttpPresenter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initPresenter();
        ihttpPresenter.attachView((V)this);
        initHttpData();
    }

    protected abstract void initHttpData();

    protected abstract void initPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("LQS", getClass().getSimpleName() + " onDestroy");
        ihttpPresenter.detachView();
    }

}
