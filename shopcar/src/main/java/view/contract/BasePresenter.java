package view.contract;

import framework.Mvp.Iview;

public
class BasePresenter<V extends Iview> implements IPresenter<V>  {
    protected  V iHttpView;
    @Override
    public void attachView(V iHttpView) {
        this.iHttpView = iHttpView;
    }

    @Override
    public void detachView() {
        this.iHttpView = null;
    }
}
