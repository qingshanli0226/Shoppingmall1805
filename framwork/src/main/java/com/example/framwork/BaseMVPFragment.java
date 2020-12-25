package com.example.framwork;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public abstract class BaseMVPFragment<T extends  IPresenter,V extends  IView> extends BaseFragment  {
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initPresenter();
        initHttpData();
    }
    protected abstract void initHttpData();

    protected abstract void initPresenter();



}
