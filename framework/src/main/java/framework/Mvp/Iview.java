package framework.Mvp;

import view.loadinPage.ErrorBean;

public
interface Iview {
    void showLoaDing();

    void hideLoading(boolean isSuccess, ErrorBean errorBean);

    void showEmpty();
}
