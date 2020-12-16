package com.example.framwork;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseMVPActivity<T extends  IPresenter,V extends  IView> extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        iniView();
        initPresenter();
        iniHttpView();
        initData();
    }

    protected abstract void iniHttpView();

    protected abstract int getLayoutId();
    protected abstract void iniView();
    protected abstract void initPresenter();

    protected abstract void initData();



}
