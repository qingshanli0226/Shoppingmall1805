package com.example.elevenmonthshoppingproject.base;

public interface IPresenter <V extends  IView>{

    void  attatch(V view);
    void detachview();

}
