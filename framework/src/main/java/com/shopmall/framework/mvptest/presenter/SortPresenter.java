package com.shopmall.framework.mvptest.presenter;

import com.shopmall.framework.callback.Sort;
import com.shopmall.framework.constart.Constart;
import com.shopmall.framework.mvptest.repository.SortRepository;
import com.shopmall.framework.view.LoadingPage;

public class SortPresenter extends Constart.SortConstartPresenter {

    public SortPresenter(Constart.SortConstartView sortConstartView) {
        super(sortConstartView);
    }

    @Override
    public void Sort(String url, final LoadingPage loadingPage) {
          mRepository.Sort(url,loadingPage, new Sort() {
            @Override
            public void Ok(Object... objects) {
                if (objects!=null){
                    mView.get().Success(objects);
                }
            }

            @Override
            public void No(String mag) {
                if (mag!=null){
                    loadingPage.showErrorPage(mag.split(",")[1]);
                    mView.get().Error(mag);
                }
            }
        });
    }

    @Override
    protected void createRepository() {
        mRepository = new SortRepository();
    }
}
