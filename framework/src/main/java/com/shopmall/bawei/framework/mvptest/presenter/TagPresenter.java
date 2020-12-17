package com.shopmall.bawei.framework.mvptest.presenter;

import com.shopmall.bawei.framework.callback.Tag;
import com.shopmall.bawei.framework.constart.Constant;
import com.shopmall.bawei.framework.logingpage.LogingPage;
import com.shopmall.bawei.framework.mvptest.repository.TagRepository;

public class TagPresenter extends Constant.TagConstartPresenter {
    public TagPresenter(Constant.TagConstartView tagConstartView) {
        super(tagConstartView);
    }

    @Override
    public void Tag(String url, final LogingPage logingPage) {
        mRepository.Tag(url,logingPage, new Tag() {
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
       mRepository=new TagRepository();
    }
}
