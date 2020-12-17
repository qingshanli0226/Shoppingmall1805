package com.shopmall.bawei.framework.mvptest.presenter;

import com.shopmall.bawei.framework.callback.Sort;
import com.shopmall.bawei.framework.constart.Constant;
import com.shopmall.bawei.framework.logingpage.LogingPage;
import com.shopmall.bawei.framework.mvptest.repository.SortRepository;

public class SortPresenter extends Constant.SortConstartPresenter {

    public SortPresenter(Constant.SortConstartView sortConstartView) {
        super(sortConstartView);
    }

    @Override
    public void Sort(String url, final LogingPage logingPage) {
          mRepository.Sort(url,logingPage, new Sort() {
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
      mRepository=new SortRepository();
    }
}
