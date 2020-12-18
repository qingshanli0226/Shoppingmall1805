package com.shopmall.bawei.framework.example.framework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.shopmall.bawei.framework.example.framework.view.LoadingPage;


public abstract class BaseActivity<P extends IPresenter,V extends IView> extends AppCompatActivity implements ToolBar.IToolBarClickListner {

    protected P httpresenter;
    protected LoadingPage logingPage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logingPage = new LoadingPage(this) {
            @Override
            protected int getsuccessId() {
                return getlayoutid();
            }
        };
        setContentView(logingPage);
        initview();
        initpreseter();
        initdate();
        if (httpresenter!=null){
            httpresenter.attchView((V)this);
        }

    }
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
    protected abstract void initpreseter();

    protected abstract void initdate();

    protected abstract void initview();

    protected abstract int getlayoutid();

    @Override
    public void onRightClick() {

    }

    @Override
    public void onLeftClick() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (httpresenter!=null){
            httpresenter.ondechView();
        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
