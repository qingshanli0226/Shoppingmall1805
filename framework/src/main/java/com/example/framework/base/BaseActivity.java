package com.example.framework.base;

import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.framework.mvp.IPresenter;
import com.example.framework.mvp.IView;


public abstract class BaseActivity<T extends IPresenter, V extends IView> extends AppCompatActivity {
    protected T presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        initPresenter();
        initView();
        initData();
        initListener();
        if(presenter!=null){
            presenter.attchView((V)this);
        }
    }

    protected abstract void initPresenter();

    protected  void initListener(){

    }

    protected abstract void initData();

    protected abstract int getLayoutID();

    protected abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ondestroy();
    }

    protected void ondestroy(){
        if(presenter!=null){
            presenter.detachView();
        }
    }
}
