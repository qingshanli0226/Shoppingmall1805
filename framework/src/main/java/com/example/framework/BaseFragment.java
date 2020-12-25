package com.example.framework;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.framework.view.LogingPage;
import com.example.framework.view.ToolBar;
import com.shopmall.bawei.framework.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public abstract class BaseFragment<P extends IPresenter,V extends IView> extends Fragment  {

    protected P httpresetnter;
    protected LogingPage logingPage;
    private ToolBar toolBar;
    private ProgressBar loadgedBar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        logingPage = new LogingPage(getContext()) {
            @Override
            protected int getsuccessId() {
                return getlayoutid();
            }
        };

        return logingPage;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onStart() {
        super.onStart();
        initstart();
        initView(logingPage.getSucessView());
        initPreseter();
        if (httpresetnter!=null) {
            httpresetnter.attchView((V) this);
        }
        initdate();

    }
    protected void initstart(){

    }

    protected abstract void initPreseter();
    protected abstract void initView(View inflate);
    protected abstract void initdate();
    protected abstract int getlayoutid();
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
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getIsConnects(Boolean isConnect){
        if (isConnect){
            onConnected();
            Log.e("Zld","2212");
        }else {
            onDeConnected();
            Log.e("Zld","212");
        }
    }

    protected void onDeConnected() {

    }

    protected void onConnected() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (httpresetnter!=null){
            httpresetnter.ondechView();

        }
        destory();
    }
    private void destory() {

    }
}
