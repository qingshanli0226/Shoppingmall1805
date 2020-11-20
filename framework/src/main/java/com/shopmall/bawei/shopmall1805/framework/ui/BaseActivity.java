package com.shopmall.bawei.shopmall1805.framework.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.shopmall.bawei.shopmall1805.framework.IPresenter;

public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity {
    protected P iPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bandLayout());
        initView();
        createPresenter();
        initData();
        initEvent();
    }
    protected void showMsg(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    protected abstract void initEvent();

    protected abstract void initData();

    protected abstract void createPresenter();

    protected abstract void initView();

    protected abstract int bandLayout();
}
