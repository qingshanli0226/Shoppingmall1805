package com.shopmall.bawei.framework.shopmall.bawei.framework.mvptest.presenter;


import com.shopmall.bawei.framework.shopmall.bawei.framework.callback.Sort;
import com.shopmall.bawei.framework.shopmall.bawei.framework.constart.Constart;
import com.shopmall.bawei.framework.shopmall.bawei.framework.mvptest.repository.SortRepository;

public class SortPresenter extends Constart.SortConstartPresenter {

    public SortPresenter(Constart.SortConstartView sortConstartView) {
        super(sortConstartView);
    }

    @Override
    public void Sort(String url) {
          mRepository.Sort(url, new Sort() {
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
      mRepository=new SortRepository();
    }
}
