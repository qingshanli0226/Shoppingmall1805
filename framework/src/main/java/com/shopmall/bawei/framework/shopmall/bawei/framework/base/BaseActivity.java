package com.shopmall.bawei.framework.shopmall.bawei.framework.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.shopmall.bawei.framework.shopmall.bawei.framework.mvp.Presenter;


public abstract class BaseActivity<P extends Presenter> extends AppCompatActivity {
           protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutid());
        initview();
        initData();
        initEnvent();
    }

    /**
     * 初始化事件
     */
    protected abstract void initEnvent();

    /**
     * 初始化view控件
     */
    protected abstract void initview();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 获取布局id
     * @return
     */
    protected abstract int layoutid();


}
