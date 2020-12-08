package mvp.view;

import mvp.view.IView;

public interface IPresenter<V extends IView> {
    void attachView(V iHttpView);
    void datachView();

}
