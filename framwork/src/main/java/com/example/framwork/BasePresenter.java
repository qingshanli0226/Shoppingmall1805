package com.example.framwork;

public class BasePresenter<V extends IView> implements IPresenter<V>{

    protected  V iview;
    @Override
    public void attatch(V view) {
        this.iview=view;
    }

    @Override
    public void detachview() {
        iview=null;
    }
}
