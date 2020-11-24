package com.shopmall.bawei.framework.mvptest.presenter;

import com.shopmall.bawei.framework.callback.Tag;
import com.shopmall.bawei.framework.constart.Constart;
import com.shopmall.bawei.framework.mvptest.repository.TagRepository;

public class TagPresenter extends Constart.TagConstartPresenter {
    public TagPresenter(Constart.TagConstartView tagConstartView) {
        super(tagConstartView);
    }

    @Override
    public void Tag(String url) {
        mRepository.Tag(url, new Tag() {
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
       mRepository=new TagRepository();
    }
}
