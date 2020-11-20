package com.bawei.framework;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

abstract public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        initData();
    }

    protected abstract void initView();

    protected abstract void initData();

    abstract int getLayoutId();


}
