package com.example.framework;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment<P extends IPresenter,V extends IVIew> extends Fragment {

    protected P httpPresenter;
    protected LogingPage logingPage;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        logingPage = new LogingPage(getContext()) {
            @Override
            protected int getsuccessId() {
                return getLayoutId();
            }
        };
        return logingPage;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView(logingPage.getSucessView());
        initPresenter();
        if (httpPresenter!=null){
            httpPresenter.attchView((V)this);
        }
        initData();
    }

    protected abstract void initView(View inflate);

    protected abstract void initPresenter();


    protected abstract int getLayoutId();

    public void showLoading(){
        logingPage.loadingPage();
    };

    public void hideLoading(){
        logingPage.showsucessPage();
    };

    public void showError(String errorName){
        logingPage.showError(errorName);
    };
    public void showEnpty(){
        logingPage.showEnptyPage();
    };

    protected abstract void initData();


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (httpPresenter!=null){
            httpPresenter.ondechView();
        }
    }
}

