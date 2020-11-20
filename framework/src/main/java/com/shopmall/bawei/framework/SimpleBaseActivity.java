package com.shopmall.bawei.framework;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class SimpleBaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        initView();
        initListener();
        initData();
    }

    protected abstract int layoutId();

    protected abstract void initView();

    protected abstract void initListener();

    protected abstract void initData();
}
