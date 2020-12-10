package com.example.framwork;

public class BaseMVPActivity<T extends  IPresenter,V extends  IView> extends  BaseActivity{
    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void iniView() {

    }

    @Override
    protected void iniData() {

    }
}
