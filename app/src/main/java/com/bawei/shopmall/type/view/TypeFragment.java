package com.bawei.shopmall.type.view;

import com.bawei.framework.BaseFragment;
import com.bawei.net.TagBean;
import com.bawei.shopmall.type.contract.TagContract;
import com.bawei.shopmall.type.contract.TagImpl;
import com.shopmall.bawei.shopmall1805.R;

public class TypeFragment<P extends TagImpl,V extends TagContract.ITagView> extends BaseFragment implements TagContract.ITagView {

    @Override
    protected int layoutId() {
        return R.layout.fragment_type;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }

    @Override
    public void onTag(TagBean tagBean) {

    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}