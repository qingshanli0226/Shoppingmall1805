package com.shopmall.framework.mvptest.presenter;

import com.shopmall.framework.callback.Home;
import com.shopmall.framework.constart.Constart;
import com.shopmall.framework.mvptest.repository.HpmeRepository;
import com.shopmall.framework.view.LoadingPage;

public class HomePresenter extends Constart.HomeConstartPresenter {

    public HomePresenter(Constart.HomeConstartView homeConstartView) {
        super(homeConstartView);
    }

    @Override
    public void homec(String url, final LoadingPage loadingPage) {
        mRepository.homec(url,loadingPage, new Home() {
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
       mRepository = new HpmeRepository();
    }
}
