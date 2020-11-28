package com.shopmall.bawei.framework.shopmall.bawei.framework.mvptest.presenter;


import com.shopmall.bawei.framework.shopmall.bawei.framework.callback.Home;
import com.shopmall.bawei.framework.shopmall.bawei.framework.constart.Constart;
import com.shopmall.bawei.framework.shopmall.bawei.framework.mvptest.repository.HpmeRepository;

public class HomePresenter extends Constart.HomeConstartPresenter {


    public HomePresenter(Constart.HomeConstartView homeConstartView) {
        super(homeConstartView);
    }

    @Override
    public void homec(String url) {
        mRepository.homec(url, new Home() {
            @Override
            public void Ok(Object... objects) {
                 if (objects!=null){
                     mView.get().Success(objects);
                 }
            }

            @Override
            public void No(String mag) {
                if (mag!=null){
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
