package com.shopmall.bawei.framework.mvptest.presenter;

import com.shopmall.bawei.framework.callback.IRegist;
import com.shopmall.bawei.framework.constart.Constart;
import com.shopmall.bawei.framework.mvptest.repository.RegistRepository;

import java.util.HashMap;

public class RegistPresenter extends Constart.RegistConstartPresenter {
    public RegistPresenter(Constart.RegistConstartView registConstartView) {
        super(registConstartView);
    }

    @Override
    public void regist(String url, HashMap<String, String> map) {
        mRepository.regist(url, map, new IRegist() {
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
           mRepository=new RegistRepository();
    }
}
