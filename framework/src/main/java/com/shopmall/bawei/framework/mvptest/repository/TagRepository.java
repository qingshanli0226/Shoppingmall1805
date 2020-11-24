package com.shopmall.bawei.framework.mvptest.repository;

import com.shopmall.bawei.framework.callback.Tag;
import com.shopmall.bawei.framework.constart.Constart;
import com.shopmall.bawei.framework.mvptest.model.TagModel;

public class TagRepository extends Constart.TagConstartRepository {
    @Override
    public void Tag(String url, Tag sort) {
         mModel.Tagdata(url,sort);
    }

    @Override
    protected void createModel() {
        mModel=new TagModel();
    }
}
