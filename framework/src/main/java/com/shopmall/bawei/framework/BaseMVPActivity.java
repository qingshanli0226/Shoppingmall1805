package com.shopmall.bawei.framework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ProgressBar;

public abstract class BaseMVPActivity<T extends IPresenter, V extends IView> extends BaseActivity {

    protected T httpPresenter;
    protected ProgressBar loadingBar;//基类来定义加载的UI的形式


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingBar = findViewById(R.id.loadingBar);//在framwork里定义这个loadingbar控件，只是为了让编译器通过检查
        initPresenter();
        if (httpPresenter!=null) {
            httpPresenter.attachView((V) this);
        }
        initData();
    }

    protected abstract void initPresenter();
    protected abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (httpPresenter!=null) {
            httpPresenter.detachView();
        }
    }
}
