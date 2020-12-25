package com.example.framwork;

import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseMVPActivity<T extends  IPresenter,V extends  IView> extends BaseActivity {
    protected ProgressBar loadingBar;//基类来定义加载的UI的形式
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        loadingBar = findViewById(R.id.loadingBar);//在framwork里定义这个loadingbar控件，只是为了让编译器通过检查
        initPresenter();
        iniHttpView();
        initData();
    }

    protected abstract void iniHttpView();

    protected abstract int getLayoutId();
    protected abstract void initPresenter();

    protected abstract void initData();



}
