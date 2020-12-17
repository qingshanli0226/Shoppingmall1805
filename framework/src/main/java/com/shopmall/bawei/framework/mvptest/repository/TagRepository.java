package com.shopmall.bawei.framework.mvptest.repository;

import com.shopmall.bawei.framework.callback.Tag;
import com.shopmall.bawei.framework.constart.Constant;
import com.shopmall.bawei.framework.logingpage.LogingPage;
import com.shopmall.bawei.framework.mvptest.model.TagModel;

public class TagRepository extends Constant.TagConstartRepository {
    @Override
    public void Tag(String url, LogingPage logingPage, Tag sort) {
         mModel.Tagdata(url,logingPage,sort);
    }

    @Override
    protected void createModel() {
        mModel=new TagModel();
    }
}
