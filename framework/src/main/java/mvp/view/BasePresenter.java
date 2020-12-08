package mvp.view;

public class BasePresenter <V extends IView> implements IPresenter<V> {

    protected V iHttpView;

    @Override
    public void attachView(V iHttpView) {
        this.iHttpView = iHttpView;
    }

    @Override
    public void datachView() {
        this.iHttpView = null;
    }
}
