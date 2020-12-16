package com.shopmall.framework.mvptest.presenter;

import com.shopmall.framework.callback.Tag;
import com.shopmall.framework.contract.Contract;
import com.shopmall.framework.mvptest.repository.TagRepository;
import com.shopmall.framework.view.LoadingPage;

public class TagPresenter extends Contract.TagConstartPresenter {

    public TagPresenter(Contract.TagConstartView tagConstartView) {
        super(tagConstartView);
    }

    @Override
    public void tag(String url, final LoadingPage loadingPage) {
        mRepository.Tag(url,loadingPage, new Tag() {
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
        mRepository = new TagRepository();
    }
}
