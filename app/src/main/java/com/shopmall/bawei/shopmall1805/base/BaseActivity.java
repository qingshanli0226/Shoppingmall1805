package com.shopmall.bawei.shopmall1805.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity<P extends IPresenter,V extends IView> extends AppCompatActivity {

    protected P httpPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        initView();
        initPresenter();
        initData();
        if (httpPresenter != null){
            httpPresenter.accatchView((V) this);
        }

    }

    private void initData() {

    }

    private void initPresenter() {
    }

    private void initView() {
    }

    protected abstract int getLayoutId();

}
