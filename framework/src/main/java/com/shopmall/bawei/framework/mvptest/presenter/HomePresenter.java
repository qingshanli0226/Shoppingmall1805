package com.shopmall.bawei.framework.mvptest.presenter;


import com.shopmall.bawei.framework.callback.Home;
import com.shopmall.bawei.framework.constart.Constant;
import com.shopmall.bawei.framework.logingpage.LogingPage;
import com.shopmall.bawei.framework.mvptest.repository.HpmeRepository;

public class HomePresenter extends Constant.HomeConstartPresenter {


    public HomePresenter(Constant.HomeConstartView homeConstartView) {
        super(homeConstartView);
    }

    @Override
    public void homec(String url, final LogingPage logingPage) {
        mRepository.homec(url,logingPage, new Home() {
            @Override
            public void Ok(Object... objects) {
                 if (objects!=null){
                     mView.get().Success(objects);
                 }
            }

            @Override
            public void No(String mag) {
                if (mag!=null){

                    logingPage.showError(mag.split(",")[1]);
                    mView.get().Error(mag);
                }

            }
        });

    }

    @Override
    protected void createRepository() {
       mRepository=new HpmeRepository();
    }
}
