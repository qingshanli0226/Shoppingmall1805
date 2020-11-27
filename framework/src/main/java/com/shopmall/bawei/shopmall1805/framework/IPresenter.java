package com.shopmall.bawei.shopmall1805.framework;

public interface IPresenter<V extends IView> {
    void attachView(V iHttpView);//将presenter和Ui建立关联，也就是绑定
    void detachView();//解除关联,避免内存泄漏
}
