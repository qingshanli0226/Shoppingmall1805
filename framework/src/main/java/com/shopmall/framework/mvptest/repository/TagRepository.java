package com.shopmall.framework.mvptest.repository;

import com.shopmall.framework.callback.Tag;
import com.shopmall.framework.constart.Constart;
import com.shopmall.framework.mvptest.model.TagModel;
import com.shopmall.framework.view.LoadingPage;

public class TagRepository extends Constart.TagConstartRepository {

    @Override
    public void Tag(String url, LoadingPage loadingPage, Tag tag) {
        mModel.Tag(url,loadingPage,tag);
    }

    @Override
    protected void createModel() {
        mModel = new TagModel();
    }
}
