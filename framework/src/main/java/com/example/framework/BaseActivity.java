package com.example.framework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity<P extends IPresenter,V extends IVIew> extends AppCompatActivity {

    protected P httpPresenter;
    protected LogingPage logingPage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        logingPage = new LogingPage(this) {
            @Override
            protected int getsuccessId() {
                return getLayoutId();
            }
        };

        setContentView(getLayoutId());
        initView();
        initData();
        if (httpPresenter!=null){
            httpPresenter.attchView((V)this);
        }


    }
    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    public void showloading(){
        logingPage.loadingPage();
    }
    public void hideLoading(){
        logingPage.showsucessPage();
    }
    public void showerror(String errorName){
        logingPage.showError(errorName);
    }
    public void showEnpty(){
        logingPage.showEnptyPage();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (httpPresenter!=null){
            httpPresenter.ondechView();
        }
    }
}
