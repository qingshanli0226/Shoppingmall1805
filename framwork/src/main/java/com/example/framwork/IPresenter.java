package com.example.framwork;

public interface IPresenter <V extends  IView>{

    void  attatch(V view);
    void detachview();

}
