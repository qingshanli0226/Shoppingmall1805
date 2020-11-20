package com.example.framework;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


public abstract class BaseActivity<P extends IPresenter,V extends IView> extends AppCompatActivity {

    protected P httpresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getlayoutid());
        initview();
        initpreseter();
        initdate();
        if (httpresenter!=null){
            httpresenter.attchView((V)this);
        }

    }

    protected abstract void initpreseter();

    protected abstract void initdate();

    protected abstract void initview();

    protected abstract int getlayoutid();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        httpresenter.ondechView();
    }
}
