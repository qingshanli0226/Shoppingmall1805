package com.shopmall.framework.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.shopmall.bawei.framework.R;
import com.shopmall.framework.mvp.Presenter;

public abstract class BaseMVPActivity<P extends Presenter> extends AppCompatActivity {
    protected P mPresenter;
    protected ProgressBar loadingBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        loadingBar = findViewById(R.id.loadingBar);//在framwork里定义这个loadingbar控件，只是为了让编译器通过检查
        initView(savedInstanceState);
        initData();
        initEvent();
    }

    protected abstract void initEvent();

    protected abstract void initData();

    protected abstract void initView(Bundle savedInstanceState);

    protected abstract int getLayoutId();

}
