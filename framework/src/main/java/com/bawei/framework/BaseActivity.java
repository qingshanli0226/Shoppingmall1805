package com.bawei.framework;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity<P extends IPresenter,V extends IView> extends AppCompatActivity{

    protected P httpPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        initView();
        initListener();
        initPresenter();
        if(httpPresenter != null){
            httpPresenter.attachView((V)this);
        }
        initData();
    }

    protected abstract int layoutId();

    protected void initView(){

    }

    protected abstract void initListener();

    protected abstract void initPresenter();

    protected void initData(){

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy();
        if(httpPresenter != null) {
            httpPresenter.detachView();
        }
    }

    protected void destroy(){

    }
}
