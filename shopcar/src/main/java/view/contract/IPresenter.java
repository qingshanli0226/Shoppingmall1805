package view.contract;

import framework.Mvp.Iview;

public
interface IPresenter<V extends Iview>{
    void attachView(V iHttpView);//将presenter和Ui建立关联，也就是绑定
    void detachView();//解除关联,避免内存泄漏
}
