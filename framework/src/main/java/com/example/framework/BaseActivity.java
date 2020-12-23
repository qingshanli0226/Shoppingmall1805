package com.example.framework;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.example.framework.view.LogingPage;
import com.example.framework.view.ToolBar;
import com.shopmall.bawei.framework.R;


public abstract class BaseActivity<P extends IPresenter,V extends IView> extends AppCompatActivity {

    protected P httpresenter;
    protected LogingPage  logingPage;
    private ToolBar toolBar;
    private ProgressBar loadgedBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logingPage = new LogingPage(this) {
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
    protected void onDestroy() {
        super.onDestroy();
        if (httpresenter!=null){
            httpresenter.ondechView();
        }

    }
}
