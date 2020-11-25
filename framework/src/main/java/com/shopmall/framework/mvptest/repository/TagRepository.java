package com.shopmall.framework.mvptest.repository;

import com.shopmall.framework.callback.Tag;
import com.shopmall.framework.constart.Constart;
import com.shopmall.framework.mvptest.model.TagModel;

public class TagRepository extends Constart.TagConstartRepository {

    @Override
    public void Tag(String url, Tag tag) {
        mModel.Tag(url,tag);
    }

    @Override
    protected void createModel() {
        mModel = new TagModel();
    }
}
