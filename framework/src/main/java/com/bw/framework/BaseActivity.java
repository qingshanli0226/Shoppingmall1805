package com.bw.framework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

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

    protected void initData() {

    }

    protected void initPresenter() {
    }

    protected void initView() {
    }

    protected abstract int getLayoutId();

    private void myToast(String message){
        Toast.makeText(this,message+"",Toast.LENGTH_SHORT).show();
    }

}
